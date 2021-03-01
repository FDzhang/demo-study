package com.example.demomysql.controller;

import cn.hutool.core.util.IdUtil;
import com.example.demomysql.bean.SysArea;
import com.example.demomysql.mapper.SysAreaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 区域
 *
 * @author ：zxq
 * @date ：Created in 2021/3/1 13:56
 */
@RestController
@Slf4j
public class AreaController {

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @GetMapping("area")
    public List<SysArea> test(String parentId) {
        log.info("parentId : {}", parentId);
        return sysAreaMapper.areaList(parentId);
    }

    public static void main(String[] args) {
        System.out.println(IdUtil.fastSimpleUUID());
    }
}
