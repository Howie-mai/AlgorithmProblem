package com.mh;

import jdk.nashorn.internal.objects.annotations.Function;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * ClassName：
 * Time：20/7/20 下午1:43
 * Description：
 * Author： mh
 */
public interface Main2Interface {

    /**
     * 修改注解的值
     */
    @Function(name = "zhushiName")
    public String testInterface(String a) throws Exception;
}
