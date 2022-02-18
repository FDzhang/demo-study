package com.example.demoasync.async;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhangxinqiang
 * @create 2022/2/17 13:17
 */
@SpringBootTest
class AsyncControllerTest {

    @Resource
    private AsyncController asyncController;

    @Test
    void test1() throws ExecutionException, InterruptedException {
        String res = asyncController.completableFutureTask();
        System.err.println(res);
    }


}