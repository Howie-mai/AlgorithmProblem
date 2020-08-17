package com.mh.bean;

/**
 * ClassName：
 * Time：20/7/31 下午4:58
 * Description：
 * Author： mh
 */
public class AddressDeapClone implements Cloneable {

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

    public AddressDeapClone(Integer id, String city) {
        this.id = id;
        this.city = city;
    }

    @Override
    protected AddressDeapClone clone() throws CloneNotSupportedException {
        return (AddressDeapClone) super.clone();
    }
}
