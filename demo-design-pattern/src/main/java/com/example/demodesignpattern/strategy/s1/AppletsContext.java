package com.example.demodesignpattern.strategy.s1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：zxq
 * @date ：Created in 2021/3/18 17:33
 */
@Service
@Slf4j
public class AppletsContext {


    private final Map<String, Applets> appletsMap = new ConcurrentHashMap<>();

    public AppletsContext() {
    }

    @Autowired
    public AppletsContext(Map<String, Applets> appletsMap) {
        this.appletsMap.clear();
        appletsMap.forEach(this.appletsMap::put);
    }

    public String code2Token(String code, String from) {
        if (!appletsMap.containsKey(from)) {
            log.warn("无对应策略：{}", from);
            return null;
        }

        Applets applets = appletsMap.get(from);
        return applets.code2Token(code);
    }

    public String decryptContent(String content, String from) {
        return appletsMap.get(from).decryptContent(content);
    }
}
