package com.mh.bean;

/**
 * ClassName：
 * Time：20/7/31 下午4:58
 * Description：
 * Author： mh
 */
public class AdminClone implements Cloneable {

    private Integer id;

    private String name;

    private AddressClone address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressClone getAddress() {
        return address;
    }

    public void setAddress(AddressClone address) {
        this.address = address;
    }

    public AdminClone(Integer id, String name, AddressClone address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
