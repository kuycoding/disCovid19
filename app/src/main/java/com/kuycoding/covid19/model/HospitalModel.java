package com.kuycoding.covid19.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HospitalModel implements Parcelable {
    String namaRs, alamatRs, route, web, telp;

    public HospitalModel() {
    }

    public HospitalModel(String namaRs, String alamatRs, String route, String web, String telp) {
        this.namaRs = namaRs;
        this.alamatRs = alamatRs;
        this.route = route;
        this.web = web;
        this.telp = telp;
    }

    protected HospitalModel(Parcel in) {
        namaRs = in.readString();
        alamatRs = in.readString();
        route = in.readString();
        web = in.readString();
        telp = in.readString();
    }

    public static final Creator<HospitalModel> CREATOR = new Creator<HospitalModel>() {
        @Override
        public HospitalModel createFromParcel(Parcel in) {
            return new HospitalModel(in);
        }

        @Override
        public HospitalModel[] newArray(int size) {
            return new HospitalModel[size];
        }
    };

    public String getNamaRs() {
        return namaRs;
    }

    public void setNamaRs(String namaRs) {
        this.namaRs = namaRs;
    }

    public String getAlamatRs() {
        return alamatRs;
    }

    public void setAlamatRs(String alamatRs) {
        this.alamatRs = alamatRs;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namaRs);
        dest.writeString(alamatRs);
        dest.writeString(route);
        dest.writeString(web);
        dest.writeString(telp);
    }
}
