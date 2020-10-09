package com.mh;

import jdk.nashorn.internal.objects.annotations.Function;

import javax.xml.bind.annotation.XmlValue;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * ClassName：
 * Time：20/7/20 下午1:43
 * Description：
 * Author： mh
 */
public class Main2 {

    public static void main(String[] args) throws Exception {
        new Main2().getDistanceFromSteps(111,222,333);
    }

    /**
     * 修改注解的值
     */
    @Function(name = "zhushiName")
    public String testInterface(int a) throws Exception{
        Method method = Main2.class.getMethod("testInterface", int.class);
        Function testA = method.getAnnotation(Function.class);

        if (testA == null){
            throw new RuntimeException("please add testA");
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(testA);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        String val = (String) memberValues.get("name");
        System.out.println("修改前" + val);
        val = "b";
        memberValues.put("name", val);
        System.out.println("修改后" + (String) memberValues.get("name"));

        return val;
    }

    public float getDistanceFromSteps(int steps, int gender, int height) throws Exception {
        float genderParameter = gender == 2 ? 0.85F : 0.8F;
        float basicStrideLength = genderParameter * (float) height;
        float strideRate = 0.0F;
        if (steps >= 180) {
            strideRate = 0.5F;
        } else if (steps >= 162) {
            strideRate = 0.73F;
        } else if (steps >= 140) {
            strideRate = 0.68F;
        } else if (steps >= 120) {
            strideRate = 0.55F;
        } else if (steps >= 90) {
            strideRate = 0.5F;
        } else if (steps >= 80) {
            strideRate = 0.45F;
        } else {
            strideRate = 0.42F;
        }
        return (float) steps * strideRate * basicStrideLength / 100000.0F;
    }

}
