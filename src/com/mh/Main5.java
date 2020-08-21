package com.mh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName：
 * Time：20/8/20 下午5:01
 * Description：
 *
 * @author mh
 */
public class Main5 {

    public static void main(String[] args) {
        HashMap<String,Object> hashMap1 = new HashMap<>();
        HashMap<String,Object> hashMap2 = new HashMap<>();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        hashMap1.put("list",list);

        hashMap2.put("list",hashMap1.get("list"));

        System.out.println(hashMap2.get("list"));

    }
}
