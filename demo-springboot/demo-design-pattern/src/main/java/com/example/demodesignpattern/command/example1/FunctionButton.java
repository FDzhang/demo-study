package com.example.demodesignpattern.command.example1;

import lombok.Data;

/**
 * 功能键类：请求发送者
 *
 * @author zhangxinqiang
 * @create 2021/5/14 11:08
 */
@Data
public class FunctionButton {
    //功能键名称
    private String name;
    //维持一个抽象命令对象的引用
    private Command command;

    public FunctionButton(String name) {
        this.name = name;
    }

    //发送请求的方法
    public void onClick() {
        System.out.print("点击功能键：");
        command.execute();
    }
}
