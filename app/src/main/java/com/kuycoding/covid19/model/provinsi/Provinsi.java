package com.kuycoding.covid19.model.provinsi;

import com.google.gson.annotations.SerializedName;

public class Provinsi {
    @SerializedName("attributes")
    private Attributes attributes;

    public Provinsi(Attributes attributes) {
        this.attributes = attributes;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
    @Override
    public String toString(){
        return
                "Provinsi{" +
                        "attributes = '" + attributes + '\'' +
                        "}";
    }
}
