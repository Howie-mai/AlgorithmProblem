package com.mh.bean;

import java.io.Serializable;

/**
 * ClassName：
 * Time：20/7/31 下午4:49
 * Description：
 * Author： mh
 */
public class Address implements Serializable {

    private Integer id;
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address(Integer id, String city) {
        this.id = id;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
