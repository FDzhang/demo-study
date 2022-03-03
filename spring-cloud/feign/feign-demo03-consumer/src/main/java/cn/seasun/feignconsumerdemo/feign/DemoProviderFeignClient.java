package cn.seasun.feignconsumerdemo.feign;

import cn.seasun.feignproviderdemoapi.api.ProviderService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : zxq
 * @create : 2022/3/2 22:10
 */
@FeignClient(name = "demo-provider")
public interface DemoProviderFeignClient extends ProviderService {

//    @GetMapping("/echo")
//    String echo(@RequestParam("name") String name);

}
