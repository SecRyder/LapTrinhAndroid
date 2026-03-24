package com.ptithcm.n22dcat040_tranthianhnguyet.models;

public class Book {
    private String tenSach;
    private String tenTacGia;
    private String gia;
    private int hinh;

    public Book(String tenSach, String tenTacGia, String gia, int hinh) {
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.gia = gia;
        this.hinh = hinh;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

}
