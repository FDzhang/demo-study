package com.example.demodesignpattern.command;

import lombok.Data;

/**
 * @author zhangxinqiang
 * @create 2021/5/14 10:58
 */
public class Invoker {
    private Command command;

    // 构造注入
    public Invoker(Command command) {
        this.command = command;
    }

    // 设值注入
    public void setCommand(Command command) {
        this.command = command;
    }

    // 业务方法，用于调用命令列的execute()方法
    public void call() {
        command.execute();
    }
}
