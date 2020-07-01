package com.example.demoelasticsearch.dao;

import com.example.demoelasticsearch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author ：zxq
 * @date ：Created in 2020/6/29 17:37
 */

public interface UserMapper extends ElasticsearchRepository<User, String> {
    List<User> findByNameLike(String keyword);
}
