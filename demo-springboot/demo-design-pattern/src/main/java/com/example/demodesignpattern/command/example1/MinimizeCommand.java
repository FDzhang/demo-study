package com.example.demodesignpattern.command.example1;


/**
 * @author zhangxinqiang
 * @create 2021/5/14 11:57
 */
public class MinimizeCommand extends Command {
    private WindowHandler windowHandler;

    public MinimizeCommand() {
        this.windowHandler = new WindowHandler();
    }

    @Override
    public void execute() {
        windowHandler.minimize();
    }
}
