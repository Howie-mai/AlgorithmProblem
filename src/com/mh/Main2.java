package com.mh;

import com.mh.bean.Address;
import jdk.nashorn.internal.objects.annotations.Function;

import javax.xml.bind.annotation.XmlValue;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * ClassName：
 * Time：20/7/20 下午1:43
 * Description：
 * Author： mh
 */
public class Main2 implements Main2Interface {

    public static void main(String[] args) throws Exception {
//        new Main2().getDistanceFromSteps(111,222,333);
//        new Main2().testInterface("hhhhh");
//        new Main2().demo(0);
        List<Address> resultList = new ArrayList<>();
        List<String> filterList = new ArrayList<>();

        resultList.add(new Address(1,"哈哈哈1"));
        resultList.add(new Address(2,"哈哈哈2"));
        resultList.add(new Address(3,"哈哈哈3"));
        resultList.add(new Address(4,"哈哈哈4"));
        resultList.add(new Address(4,"么么哒"));
        resultList.add(new Address(2,"嘤嘤嘤"));

        System.out.println(resultList);

        filterList.add("么么哒");
        filterList.add("嘤嘤嘤");

        resultList.sort((o1, o2) -> {
            int i = o2.getId().compareTo(o1.getId());
            if(i != 0){
                return i;
            }else if(filterList.contains(o2.getCity()) && !filterList.contains(o1.getCity())){
                return 1;
            }else if(filterList.contains(o1.getCity()) && !filterList.contains(o2.getCity())){
                return -1;
            }
            return i;
        });

        System.out.println(resultList);


    }

    public void demo(int status) {
        status = 1;
        System.out.println(status);
    }

    /**
     * 修改注解的值
     */
    @Override
    public String testInterface(String a) throws Exception {
        Method method = Main2.class.getMethod("testInterface", String.class);
        Function testA = method.getAnnotation(Function.class);

        if (testA == null) {
            throw new RuntimeException("please add testA");
        }
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(testA);
        Field value = invocationHandler.getClass().getDeclaredField("memberValues");
        value.setAccessible(true);
        Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
        String val = (String) memberValues.get("name");
        System.out.println("修改前" + val);
        val = a;
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
