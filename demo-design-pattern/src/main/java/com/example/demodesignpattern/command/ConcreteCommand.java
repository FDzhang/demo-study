package com.example.demodesignpattern.command;

import lombok.Data;

/**
 * @author zhangxinqiang
 * @create 2021/5/14 11:00
 */
public class ConcreteCommand extends Command {
    // 维持一个对请求接收者对象的引用
    private Receiver receiver;

    @Override
    public void execute() {
        // 调用请求接收者的业务处理方法action()
        receiver.action();
    }
}
