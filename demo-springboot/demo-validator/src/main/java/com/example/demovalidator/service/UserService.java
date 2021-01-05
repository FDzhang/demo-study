package com.example.demovalidator.service;

import com.example.demovalidator.bean.User;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author ：zxq
 * @date ：Created in 2021/1/5 16:56
 */

public interface UserService {
    Long saveUser(@Valid @NotNull User user);

    /**
     * 批量保存EXCEL中的数据
     */
    void saveUsersFromExcel();
}
