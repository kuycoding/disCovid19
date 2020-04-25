package com.kuycoding.covid19.model;

import com.google.gson.annotations.SerializedName;

public class CountryModel {
    @SerializedName("name")
    private String namel;

    @SerializedName("positif")
    private String  idnConfirmed;

    @SerializedName("sembuh")
    private String idnRecovered;

    @SerializedName("meninggal")
    private String idnDeaths;

    public CountryModel(String namel, String idnConfirmed, String idnRecovered, String idnDeaths) {
        this.namel = namel;
        this.idnConfirmed = idnConfirmed;
        this.idnRecovered = idnRecovered;
        this.idnDeaths = idnDeaths;
    }

    public String getNamel() {
        return namel;
    }

    public void setNamel(String namel) {
        this.namel = namel;
    }

    public String getIdnConfirmed() {
        return idnConfirmed;
    }

    public void setIdnConfirmed(String idnConfirmed) {
        this.idnConfirmed = idnConfirmed;
    }

    public String getIdnRecovered() {
        return idnRecovered;
    }

    public void setIdnRecovered(String idnRecovered) {
        this.idnRecovered = idnRecovered;
    }

    public String getIdnDeaths() {
        return idnDeaths;
    }

    public void setIdnDeaths(String idnDeaths) {
        this.idnDeaths = idnDeaths;
    }
}
