package com.example.demomysql.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.example.demomysql.bean.SysArea;
import com.example.demomysql.bean.SysOffice;
import com.example.demomysql.mapper.SysAreaMapper;
import com.example.demomysql.mapper.SysOfficeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 机构
 * 1. 添加数据
 *
 * @author ：zxq
 * @date ：Created in 2021/3/1 14:23
 */
@RestController
@Slf4j
public class SysOfficeController {

    @Autowired
    private SysOfficeMapper sysOfficeMapper;

    @Autowired
    private SysAreaMapper sysAreaMapper;

    @GetMapping("office/addAll")
    public String office() {
        insertOffice();
        return "success";
    }

    public void insertOffice() {
        String areaParentId = "";
        String officeParentId = "";
        // 5:医院
        String type = "5";
        int code = 5000;

        List<SysArea> areaIdList = sysAreaMapper.areaList(areaParentId);


        for (SysArea area : areaIdList) {
            SysOffice sysOffice = new SysOffice();

            sysOffice.setId(IdUtil.fastSimpleUUID());
            sysOffice.setParentId(officeParentId);
            sysOffice.setParentIds("0," + officeParentId + ",");
            sysOffice.setCode(String.valueOf(code++));
            sysOffice.setSort(30.0);
            sysOffice.setGrade("1");
            sysOffice.setType(type);

            sysOffice.setName(area.getName());
            sysOffice.setAreaId(area.getId());

            sysOffice.setCreateBy("admin");
            sysOffice.setUpdateBy("admin");
            sysOffice.setCreateDate(new Date());
            sysOffice.setUpdateDate(new Date());
            sysOffice.setDelFlag("0");

            System.out.println(JSON.toJSONString(sysOffice));
            sysOfficeMapper.insertSelective(sysOffice);
        }

        log.info("完成机构添加： {} 个", areaIdList.size());
    }

}
