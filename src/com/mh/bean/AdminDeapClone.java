package com.mh.bean;

/**
 * ClassName：
 * Time：20/7/31 下午4:58
 * Description：
 * Author： mh
 */
public class AdminDeapClone implements Cloneable {

    private Integer id;

    private String name;

    private AddressDeapClone address;

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

    public AddressDeapClone getAddress() {
        return address;
    }

    public void setAddress(AddressDeapClone address) {
        this.address = address;
    }

    public AdminDeapClone(Integer id, String name, AddressDeapClone address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public AdminDeapClone clone() throws CloneNotSupportedException {

        AdminDeapClone adminDeapClone = (AdminDeapClone) super.clone();
        adminDeapClone.setAddress(this.address.clone());
        return adminDeapClone;
    }
}
