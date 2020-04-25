package com.kuycoding.covid19.model.provinsi;

import com.google.gson.annotations.SerializedName;

public class Attributes {
    @SerializedName("FID")
    private String fID;

    @SerializedName("Kode_Provi")
    private String kodeProvi;

    @SerializedName("Kasus_Meni")
    private String kasusMeni;

    @SerializedName("Kasus_Posi")
    private String kasusPosi;

    @SerializedName("Provinsi")
    private String provinsi;

    @SerializedName("Kasus_Semb")
    private String kasusSemb;

    public Attributes(String fID, String kodeProvi, String kasusMeni, String kasusPosi, String provinsi, String kasusSemb) {
        this.fID = fID;
        this.kodeProvi = kodeProvi;
        this.kasusMeni = kasusMeni;
        this.kasusPosi = kasusPosi;
        this.provinsi = provinsi;
        this.kasusSemb = kasusSemb;
    }

    public String getfID() {
        return fID;
    }

    public void setfID(String fID) {
        this.fID = fID;
    }

    public String getKodeProvi() {
        return kodeProvi;
    }

    public void setKodeProvi(String kodeProvi) {
        this.kodeProvi = kodeProvi;
    }

    public String getKasusMeni() {
        return kasusMeni;
    }

    public void setKasusMeni(String kasusMeni) {
        this.kasusMeni = kasusMeni;
    }

    public String getKasusPosi() {
        return kasusPosi;
    }

    public void setKasusPosi(String kasusPosi) {
        this.kasusPosi = kasusPosi;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKasusSemb() {
        return kasusSemb;
    }

    public void setKasusSemb(String kasusSemb) {
        this.kasusSemb = kasusSemb;
    }

    @Override
    public String toString(){
        return
                "Attributes{" +
                        "fID = '" + fID + '\'' +
                        ",kode_Provi = '" + kodeProvi + '\'' +
                        ",kasus_Meni = '" + kasusMeni + '\'' +
                        ",kasus_Posi = '" + kasusPosi + '\'' +
                        ",provinsi = '" + provinsi + '\'' +
                        ",kasus_Semb = '" + kasusSemb + '\'' +
                        "}";
    }
}
