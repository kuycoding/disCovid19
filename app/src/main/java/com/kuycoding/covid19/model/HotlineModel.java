package com.kuycoding.covid19.model;

public class HotlineModel {
    private String nama, noPhone;

    public HotlineModel() {
    }

    public HotlineModel(String nama, String noPhone) {
        this.nama = nama;
        this.noPhone = noPhone;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoPhone() {
        return noPhone;
    }

    public void setNoPhone(String noPhone) {
        this.noPhone = noPhone;
    }
}
