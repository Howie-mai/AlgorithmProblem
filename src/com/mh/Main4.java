package com.mh;

import com.mh.bean.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName：
 * Time：20/7/28 下午4:10
 * Description：深克隆与浅克隆
 * Author： mh
 */
public class Main4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address address= new Address(1,"广州");
        Admin admin1 = new Admin(1,"Java",address);

        // 浅克隆
        Admin admin2 = new Admin(admin1.getId(),admin1.getName(),admin1.getAddress());

        // 深克隆
        Admin admin3 = new Admin(admin1.getId(),admin1.getName(),
                                new Address(admin1.getAddress().getId(),admin1.getAddress().getCity()));

        admin1.getAddress().setCity("湛江");

        admin1.setName("PHP");

        System.out.println("admin1： " + admin1.getAddress().getCity() + " " + admin1.getName());
        System.out.println("admin2： " + admin2.getAddress().getCity() + " " + admin2.getName());
        System.out.println("admin3： " + admin3.getAddress().getCity() + " " + admin3.getName());

        // 浅克隆
        AddressClone addressClone= new AddressClone(1,"广州");
        AdminClone admin4 = new AdminClone(1,"Java",addressClone);

        AdminClone admin5 = (AdminClone) admin4.clone();

        admin4.getAddress().setCity("湛江");
        admin4.setName("PHP");

        System.out.println("admin4： " + admin4.getAddress().getCity() + " " + admin4.getName());
        System.out.println("admin5： " + admin5.getAddress().getCity() + " " + admin5.getName());

        // 深克隆
        AddressDeapClone addressDeapClone = new AddressDeapClone(1,"广州");
        AdminDeapClone admin6 = new AdminDeapClone(1,"JAVA",addressDeapClone);

        AdminDeapClone admin7 = admin6.clone();

        admin6.getAddress().setCity("湛江 ");

        admin6.setName("PHP");

        System.out.println("admin6： " + admin6.getAddress().getCity() + " " + admin6.getName());
        System.out.println("admin7： " + admin7.getAddress().getCity() + " " + admin7.getName());

        // 深克隆
        Admin admin8 = new Admin(1,"JAVA",address);

        Admin admin9 = StreamClone.clone(admin8);

        admin8.getAddress().setCity("湛江");

        System.out.println("admin8： " + admin8.getAddress().getCity());
        System.out.println("admin9： " + admin9.getAddress().getCity());
    }

    static class StreamClone{
        public static <T extends Serializable> T clone(Admin obj){
            T cloneObj = null;
            try{
                ByteOutputStream bo = new ByteOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bo);
                oos.writeObject(obj);
                oos.close();

                ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
                ObjectInputStream oi = new ObjectInputStream(bi);
                cloneObj = (T)oi.readObject();

                bo.close();
                oos.close();

                bi.close();
                oi.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return cloneObj;
        }
    }
}
