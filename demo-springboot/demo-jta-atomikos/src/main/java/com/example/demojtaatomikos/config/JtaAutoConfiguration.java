package com.example.demojtaatomikos.config;


import com.example.demojtaatomikos.manager.JtaUserTransaction;
import com.example.demojtaatomikos.util.MongoUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * @author ：zxq
 * @date ：Created in 2020/12/4 16:51
 */
@Configuration
public class JtaAutoConfiguration {

    @Bean
    @Primary
    public JtaUserTransaction jtaUserTransaction(MongoDatabaseFactory factory, MongoUtils mongoUtils) {
        return new JtaUserTransaction(new MongoTransactionManager(factory), mongoUtils);
    }

}
