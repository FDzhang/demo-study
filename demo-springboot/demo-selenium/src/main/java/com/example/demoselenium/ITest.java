package com.example.demoselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author zhangxinqiang
 * @create 2021/8/3 15:21
 */
public class ITest {


    public static void main(String[] args) {

            WebDriver driver = new ChromeDriver();
            driver.get("http://www.itest.info");

            String title = driver.getTitle();
            System.out.printf(title);

            driver.close();
        }

}
