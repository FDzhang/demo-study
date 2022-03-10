package cn.seasun.nacosconfigdemo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : zxq
 * @create : 2022/3/10 22:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void encode() {
        // 第一个加密
        String password = "woshimima";
        System.out.println(encryptor.encrypt(password));

        // 第二个加密
        password = "bushimima";
        System.out.println(encryptor.encrypt(password));
    }

    @Value("${xxx-password:}")
    private String xxxPassword;

    @Test
    public void print() {
        System.out.println(xxxPassword);
    }

}