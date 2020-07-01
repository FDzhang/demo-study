package com.example.demoelasticsearch.controller;

import com.example.demoelasticsearch.dao.UserMapper;
import com.example.demoelasticsearch.model.Result;
import com.example.demoelasticsearch.model.User;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * @author ：zxq
 * @date ：Created in 2020/6/29 17:26
 */
@RestController
@RequestMapping(value = "/")
public class TestController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 全文搜索
     *
     * @param keyword 关键字
     * @param page    当前页，从0开始
     * @param size    每页大小
     * @return {@link Result} 接收到的数据格式为json
     */
    @GetMapping("/full")
    public Result full(String keyword,Integer page, Integer size) {
        // 校验参数
        if (StringUtils.isEmpty(page)) {
            // if page is null, page = 0
            page = 0;
        }

        if (StringUtils.isEmpty(size)) {
            // if size is null, size default 10
            size = 10;
        }

        // 构造分页类
        Pageable pageable = PageRequest.of(page, size);

        // 构造查询 NativeSearchQueryBuilder
        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder()
                .withPageable(pageable);
        if (!StringUtils.isEmpty(keyword)) {
            // keyword must not null
            searchQueryBuilder.withQuery(QueryBuilders.queryStringQuery(keyword));
        }

    /*
    SearchQuery
    这个很关键，这是搜索条件的入口，
    elasticsearchTemplate 会 使用它 进行搜索
     */
        SearchQuery searchQuery = searchQueryBuilder.build();

        // page search
        Page<User> phoneModelPage = elasticsearchTemplate.queryForPage(searchQuery, User.class);

        // return
        return Result.success(phoneModelPage);
    }


    @GetMapping("/search/name1")
    public Result searchTitle(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return Result.error();
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(keyword))
                .build();
        List<User> list = elasticsearchTemplate.queryForList(searchQuery, User.class);
        return Result.success(list);
    }


    @PostMapping("add")
    public String test(@RequestBody User user) {
        userMapper.save(user);
        return "success";
    }

    @GetMapping("get/{id}")
    public User test1(@PathVariable String id) {
        Optional<User> userOptional = userMapper.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user;
        }
        return null;
    }

    @GetMapping("/getall")
    public Result getAll() {
        Iterable<User> iterable = userMapper.findAll();
        List<User> list = new ArrayList<>();
        iterable.forEach(list::add);
        System.out.println(list.size());
        return Result.success(list);
    }

    @PostMapping("/update")
    public Result updateById(@RequestBody User user) {
        String id = user.getId();
        if (StringUtils.isEmpty(id)) {
            return Result.error();
        }
        userMapper.save(user);
        return Result.success();
    }

    @GetMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error();
        }
        userMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping("/delete")
    public Result deleteAll() {
        userMapper.deleteAll();
        return Result.success();
    }


    /**
     * 搜索标题中的关键字
     *
     * @param keyword
     * @return
     */
    @GetMapping("/search/name")
    public Result repSearchTitle(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return Result.error();
        }
        return Result.success(userMapper.findByNameLike(keyword));
    }


}
