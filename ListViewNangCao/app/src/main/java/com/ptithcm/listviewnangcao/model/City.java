package com.ptithcm.listviewnangcao.model;

public class City {
    private String nameCity;
    private int hinh;
    private String linkHinh;

    // Ham khoi tao
    public City(String nameCity, int hinh, String linkHinh){
        this.nameCity = nameCity;
        this.hinh = hinh;
        this.linkHinh = linkHinh;
    }

    // Ham getter, setter
    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getLinkHinh() {
        return linkHinh;
    }

    public void setLinkHinh(String linkHinh) {
        this.linkHinh = linkHinh;
    }
}
