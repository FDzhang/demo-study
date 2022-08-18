package com.example.demodesignpattern.command.example1;

/**
 * 帮助命令类：具体命令类
 *
 * @author zhangxinqiang
 * @create 2021/5/14 11:15
 */
public class HelpCommand extends Command {
    //维持对请求接收者的引用
    private HelpHandler helpHandler;

    public HelpCommand() {
        helpHandler = new HelpHandler();
    }

    // 命令执行方法，将调用请求接收者的业务方法
    @Override
    public void execute() {
        helpHandler.display();
    }
}
