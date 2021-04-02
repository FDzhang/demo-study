package com.example.demoelasticjob.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * @author ：zxq
 * @date ：Created in 2021/4/2 16:35
 */

public class MyJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                System.out.println("000");
                break;
            case 1:
                // do something by sharding item 1
                System.out.println("111");
                break;
            case 2:
                // do something by sharding item 2
                System.out.println("222");
                break;
            // case n: ...
            default:
                break;
        }
    }
}