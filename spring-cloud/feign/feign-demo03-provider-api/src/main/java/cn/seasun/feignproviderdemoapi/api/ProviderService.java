package cn.seasun.feignproviderdemoapi.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangxinqiang
 * @create 2022/3/3 17:20
 */
public interface ProviderService {

    @GetMapping("/echo")
    String echo(@RequestParam("name") String name);

}
