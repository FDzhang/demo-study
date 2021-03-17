package com.example.demoredis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@ConditionalOnClass(RedisTemplate.class)
public class RedisConfig {

//    @Value("${spring.redis.cluster.nodes}")
//    private String host;
//    @Value("${spring.redis.cluster.max-redirects}")
//    private String redirects;
//    @Value("${spring.redis.timeout}")
//    private String timeout;
//    @Value("${spring.redis.password}")
//    private String password;

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        StringRedisTemplate template = new StringRedisTemplate();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        template.setEnableTransactionSupport(true);
//        return template;
//    }

//    /**
//     * redis集群配置
//     */
//    @Bean
//    public RedisClusterConfiguration getClusterConfiguration() {
//        if (host.split(",").length > 1) {
//            //如果是host是集群模式的才进行以下操作
//            Map<String, Object> source = new HashMap<String, Object>();
//
//            source.put("spring.redis.cluster.nodes", host);
//
//            source.put("spring.redis.cluster.timeout", timeout.replace("ms", ""));
//
//            source.put("spring.redis.cluster.max-redirects", redirects);
//
//            source.put("spring.redis.cluster.password", password);
//
//            return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
//        } else {
//            return null;
//        }
//
//    }
//
//    /**
//     * 集群遍历
//     */
//    @Bean
//    public LettuceConnectionFactory lettuceConnectionFactory() {
//        if (host.split(",").length == 1) {
//            LettuceConnectionFactory factory = new LettuceConnectionFactory();
//            factory.setHostName(host.split(":")[0]);
//            factory.setPort(Integer.valueOf(host.split(":")[1]));
//            factory.setPassword(password);
//            factory.setTimeout(Integer.parseInt(timeout.replace("ms", "")));
//            return factory;
//        } else {
//            LettuceConnectionFactory jcf = new LettuceConnectionFactory(getClusterConfiguration());
//            jcf.setPassword(password); //集群的密码认证
//            return jcf;
//        }
//    }
}
