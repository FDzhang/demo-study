package com.example.demoxxljob.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * xxl-job 类形式
 * @author ：zxq
 * @date ：Created in 2020/5/8 11:25
 */
@Component
public class SimpleJob2 extends IJobHandler {
    private static Logger logger = LoggerFactory.getLogger(SimpleJob2.class);

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        logger.info("类形式 job");
        return ReturnT.SUCCESS;
    }
}
