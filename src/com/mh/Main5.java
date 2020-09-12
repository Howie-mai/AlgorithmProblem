package com.mh;

import com.mh.bean.User;

import java.util.ArrayList;
import java.util.Date;
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
        Main5 main5 = new Main5();
        main5.test();

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(null);
        list2.add(4);
        System.out.println(main5.compareList(list1,list2));
    }

    public void test(){
        List<User> list = new ArrayList<>();
        for (int i = 0;i < 5;i++){
            User user = new User();
            user.setId(i);
            list.add(user);
        }

        System.out.println(list);

        for (User user : list) {
            setName(user);
        }

        System.out.println("========");
        System.out.println(list);
    }

    private void setName(User user) {
        user.setName("mh");
        user.setCreateTime(new Date());
    }

    public boolean compareList(List<Integer> list1,List<Integer> list2){
        return list1.equals(list2);
    }



}
