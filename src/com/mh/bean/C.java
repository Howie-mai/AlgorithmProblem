package com.mh.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName：
 * Time：20/10/23 上午11:09
 * Description：
 *
 * @author mh
 */
public class C {
    public C() {
        System.out.println("C class");
    }

    public static void main(String[] args) {
        List<Admin> list = new ArrayList<>();
        list.add(new Admin(1,"mh",new Address(2,"gz")));
        list.add(new Admin(2,"mh",new Address(2,"gz")));
        list.add(new Admin(3,"mh",new Address(2,"gz")));
        list.add(new Admin(4,"mh",new Address(2,"gz")));

        List<Admin> list2 = new ArrayList<>();
        int num = 5;
        for (Admin admin:list) {
            admin.setId(num++);
            list2.add(admin);
        }
        for (Admin admin : list) {
            System.out.println(admin);
        }
        for (Admin admin : list2) {
            System.out.println(admin);
        }
    }
}
