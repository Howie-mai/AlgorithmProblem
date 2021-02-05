package com.mh.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName：
 * Time：2021/2/4 3:56 下午
 * Description：
 *
 * @author mh
 */
public class BeanMap {
    Map<String,Object> map;
    int num;

    public BeanMap(Map<String, Object> map) {
        this.map = map;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "BeanMap{" +
                "map=" + map +
                ", num=" + num +
                '}';
    }

    public static void main(String[] args) {
        List<BeanMap> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("num",1);
        list.add(new BeanMap(new HashMap<>(map)));
        list.add(new BeanMap(new HashMap<>(map)));
        list.add(new BeanMap(new HashMap<>(map)));
        list.add(new BeanMap(new HashMap<>(map)));

        List<BeanMap> list2 = new ArrayList<>();
        int num = 1;
        for (BeanMap beanMap : list) {
            beanMap.setNum(num++);
            list2.add(beanMap);
        }
        System.out.println(list2);
    }
}

