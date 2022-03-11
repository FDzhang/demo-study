package cn.seasun.nacosconfigdemo.listener;

import com.alibaba.nacos.common.utils.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.context.environment.EnvironmentManager;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author : zxq
 * @create : 2022/3/10 22:48
 * 下面，我们进行下简单测试。
 * <p>
 * ① 使用 DemoApplication 启动示例应用。
 * <p>
 * 之后，访问 http://127.0.0.1:8080/demo/test 接口，返回结果为 woshimima，符合预期。
 * <p>
 * ② 然后，将 "bushimima" 加密结果 "NGgajNjjhKcgm7ncXvdVNsShSsueysdcCOTbOmtHXRc="，
 * 赋值到 Nacos 配置集 demo-application-jasypt 的 xxx-password 配置项。如下图所示：
 * <p>
 * 之后，访问 http://127.0.0.1:8080/demo/test 接口，返回结果为 ENC(NGgajNjjhKcgm7ncXvdVNsShSsueysdcCOTbOmtHXRc=)，
 * 不符合预期。理论来说，返回结果应该是 bushimima，说明 Jasypt 并未对 Nacos 自动配置刷新获取到的最新配置进行解密。
 * <p>
 * 5.7 JasyptEnvironmentChangeListener
 * 针对 Jasypt 并未对 Nacos 自动配置刷新获取到的最新配置进行解密，我们可以通过自定义 JasyptEnvironmentChangeListener 监听器，
 * 发现变更的配置项的值是使用 Jasypt 进行加密的，则进行解密，并设置到 Environment 中。代码如下：
 * <p>
 * 😈 不过这个方案暂时只是临时解决方案，因为在 JasyptEnvironmentChangeListener 处理完成之前，
 * 实际获取到经过 Jasypt 加密的配置项的值，都是未经过解密的，这个可能导致不可预期的问题，需要胖友进行一定的测试噢。
 */
@Component
public class JasyptEnvironmentChangeListener implements ApplicationListener<EnvironmentChangeEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // Environment 管理器，可以实现配置项的获取和修改
    @Autowired
    private EnvironmentManager environmentManager;

    // Jasypt 加密器，可以对配置项进行加密和加密
    @Autowired
    private StringEncryptor encryptor;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        for (String key : event.getKeys()) {
            // <1> 获得 value
            Object valueObj = environmentManager.getProperty(key);
            if (!(valueObj instanceof String)) {
                continue;
            }
            String value = (String) valueObj;
            // <2> 判断 value 是否为加密。如果是，则进行解密
            if (value.startsWith("ENC(") && value.endsWith(")")) {
                value = encryptor.decrypt(StringUtils.substringBetween(value, "ENC(", ")"));
                logger.info("[onApplicationEvent][key({}) 解密后为 {}]", key, value);
                // <3> 设置到 Environment 中
                environmentManager.setProperty(key, value);
            }
        }
    }

}
