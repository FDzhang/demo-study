package com.example.demodesignpattern.command.example1;

import java.util.ArrayList;

/**
 * @author zhangxinqiang
 * @create 2021/5/14 11:05
 */
public class FBSettingWindow {
    // 窗口标题
    private String title;

    // 定义一个ArrayList 来存储所有功能键
    private ArrayList<FunctionButton> functionButtons = new ArrayList<>();

    public FBSettingWindow(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void addFunctionButton(FunctionButton fb) {
        functionButtons.add(fb);
    }

    public void removeFunctionButton(FunctionButton fb) {
        functionButtons.remove(fb);
    }

    // 显示窗口以及功能键
    public void display() {
        System.out.println("显示窗口：" + this.title);
        System.out.println("显示功能键：");
        for (FunctionButton functionButton : functionButtons) {
            System.out.println(functionButton.getName());
        }
        System.out.println("------------------------------");
    }


}
