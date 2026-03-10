package com.ptithcm.intent.model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String Hoten;
    private int NamSinh;
    private String DiaChi;

    public SinhVien(String hoten, int namSinh, String diaChi){
        Hoten = hoten;
        NamSinh = namSinh;
        DiaChi = diaChi;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }


}
