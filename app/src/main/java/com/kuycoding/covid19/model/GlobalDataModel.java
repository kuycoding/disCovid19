package com.kuycoding.covid19.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GlobalDataModel {
    @SerializedName("provinceState")
    private String provinceState;
    @SerializedName("countryRegion")
    private String countryRegion;
    @SerializedName("lastUpdate")
    private String lastUpdate;
    @SerializedName("lat")
    private double lat;
    @SerializedName("long")
    private double lng;
    @SerializedName("confirmed")
    private String confirmed;
    @SerializedName("recovered")
    private String recovered;
    @SerializedName("deaths")
    private String deaths;
    @SerializedName("active")
    private String active;
    @SerializedName("iso2")
    private String iso2;
    @SerializedName("is3")
    private String iso3;

    public GlobalDataModel() {
    }

    public GlobalDataModel(String provinceState, String countryRegion, String lastUpdate, double lat, double lng, String confirmed, String recovered, String deaths, String active, String iso2, String iso3) {
        this.provinceState = provinceState;
        this.countryRegion = countryRegion;
        this.lastUpdate = lastUpdate;
        this.lat = lat;
        this.lng = lng;
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.active = active;
        this.iso2 = iso2;
        this.iso3 = iso3;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    @Override
    public String toString() {
        return "{" +
                "Positif = '" + confirmed + '\''
                +"}";
    }
}
