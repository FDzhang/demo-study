package com.example.demojtaatomikos.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/4 17:00
 */

@Component
@RequiredArgsConstructor
public class MongoUtils {
    private final MongoTemplate mongoTemplate;

    public void setSessionSynchronizationForTransactionBegin() {
        // 同步任何事务（即使是空事务）并在执行此操作时启动MongoDB事务
        mongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS);
    }

    public void setSessionSynchronizationForTransactionCompletion() {
        mongoTemplate.setSessionSynchronization(SessionSynchronization.ON_ACTUAL_TRANSACTION);
    }
}
