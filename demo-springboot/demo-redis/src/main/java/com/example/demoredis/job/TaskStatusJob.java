package com.example.demoredis.job;

import com.example.demoredis.util.RedisDistributeLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * 定时任务
 *
 * @author ：zxq
 * @date ：Created in 2020/11/11 13:43
 */
@Component
@EnableScheduling
@Slf4j
public class TaskStatusJob {

    /**
     * 定时任务
     */
    @Scheduled(cron = "${cron.online}")
    public void task1() {
        log.info("------task1 开始------");
        String lockName = "task_name";
        String key = RedisDistributeLock.tryLock(lockName, "59");
        if (StringUtils.hasText(key)) {
            log.info("get lock successfully with lockName:" + lockName);

            // 业务逻辑
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                RedisDistributeLock.unlock(lockName, key);
                log.info("release lock successfully with lockName:" + lockName);
            } catch (Exception e) {
                log.info("release lock fail");
                e.printStackTrace();
            }
        } else {
            log.info("get lock fail with lockKey:" + lockName);
        }
        log.info("------task1 结束------");
    }

    /**
     * 定时任务
     */
    @Scheduled(cron = "${cron.online}")
    public void task2() {
        log.info("------task2 开始------");
        String lockName = "task_name";
        String key = RedisDistributeLock.tryLock(lockName, "59");
        if (StringUtils.hasText(key)) {
            log.info("get lock successfully with lockName:" + lockName);

            // 业务逻辑
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                RedisDistributeLock.unlock(lockName, key);
                log.info("release lock successfully with lockName:" + lockName);
            } catch (Exception e) {
                log.info("release lock fail");
                e.printStackTrace();
            }
        } else {
            log.info("get lock fail with lockKey:" + lockName);
        }
        log.info("------task2 结束------");
    }

}
