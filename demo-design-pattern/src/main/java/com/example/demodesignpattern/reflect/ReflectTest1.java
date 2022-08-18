package com.example.demodesignpattern.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author zhangxinqiang
 * @create 2022/3/14 13:12
 *
 * 对于map<String, String>
 *     使用反射，可以放入String，Long
 *     但是取出时，同样需要invoke
 *     若使用对象的map.get，则会报类型映射错误 ClassCastException)
 */
public class ReflectTest1 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String, String> map = new HashMap<>();

        Class<? extends HashMap> aClass = map.getClass();
        Method put = aClass.getMethod("put", Object.class, Object.class);
        put.invoke(map, "key1", 123L);


        Method get = aClass.getMethod("get", Object.class);

        System.err.println(get.invoke(map, "key1"));
        System.err.println(map.get("key1"));
    }
}
