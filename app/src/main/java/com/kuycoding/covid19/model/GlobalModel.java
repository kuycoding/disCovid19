package com.kuycoding.covid19.model;

import com.google.gson.annotations.SerializedName;

public class GlobalModel {
    @SerializedName("lastUpdate")
    private String lastUpdate;
    @SerializedName("confirmed")
    private GlobalConfirmed globalConfirmed;
    @SerializedName("recovered")
    private GlobalRecovered globalRecovered;
    @SerializedName("deaths")
    private GlobalDeaths globalDeaths;

    public GlobalModel(String lastUpdate, GlobalConfirmed globalConfirmed, GlobalRecovered globalRecovered, GlobalDeaths globalDeaths) {
        this.lastUpdate = lastUpdate;
        this.globalConfirmed = globalConfirmed;
        this.globalRecovered = globalRecovered;
        this.globalDeaths = globalDeaths;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public GlobalConfirmed getGlobalConfirmed() {
        return globalConfirmed;
    }

    public void setGlobalConfirmed(GlobalConfirmed globalConfirmed) {
        this.globalConfirmed = globalConfirmed;
    }

    public GlobalRecovered getGlobalRecovered() {
        return globalRecovered;
    }

    public void setGlobalRecovered(GlobalRecovered globalRecovered) {
        this.globalRecovered = globalRecovered;
    }

    public GlobalDeaths getGlobalDeaths() {
        return globalDeaths;
    }

    public void setGlobalDeaths(GlobalDeaths globalDeaths) {
        this.globalDeaths = globalDeaths;
    }

    public class GlobalRecovered {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public GlobalRecovered(int value, String detail) {
            this.value = value;
            this.detail = detail;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    public class GlobalConfirmed {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public GlobalConfirmed(int value, String detail) {
            this.value = value;
            this.detail = detail;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }

    public class GlobalDeaths {
        @SerializedName("value")
        private int value;
        @SerializedName("detail")
        private String detail;

        public GlobalDeaths(int value, String detail) {
            this.value = value;
            this.detail = detail;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
    }
}
