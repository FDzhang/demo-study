package com.example.demodesignpattern.command.example1;

/**
 * @author zhangxinqiang
 * @create 2021/5/14 13:14
 */
public class Client {
    public static void main(String[] args) {
        FBSettingWindow settingWindow = new FBSettingWindow("功能键设置");

        FunctionButton fb1 = new FunctionButton("功能键1");
        FunctionButton fb2 = new FunctionButton("功能键2");

        Command c1 = new HelpCommand();
        Command c2 = new MinimizeCommand();

        fb1.setCommand(c1);
        fb2.setCommand(c2);

        settingWindow.addFunctionButton(fb1);
        settingWindow.addFunctionButton(fb2);

        settingWindow.display();

        fb1.onClick();
        fb2.onClick();
    }
}
