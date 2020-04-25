package com.kuycoding.covid19.api;

import com.kuycoding.covid19.model.Confirmed;
import com.kuycoding.covid19.model.CountryModel;
import com.kuycoding.covid19.model.Death;
import com.kuycoding.covid19.model.GlobalDataModel;
import com.kuycoding.covid19.model.GlobalModel;
import com.kuycoding.covid19.model.Recovered;
import com.kuycoding.covid19.model.provinsi.Provinsi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoint {
    @GET(Api.MATHDROID_END_POINT_SUMMARY_WORLD)
    Call<GlobalModel> getSummaryGlobal();

    @GET(Api.MATHDROID_END_POINT_CONFIRM)
    Call<List<GlobalDataModel>> getGlobalData();

    @GET(Api.MATHDROID_END_POINT_CONFIRM)
    Call<List<Confirmed>> getGlobalConfirmed();

    @GET(Api.MATHDROID_END_POINT_RECOVERED)
    Call<List<Recovered>> getGlobalRecovered();

    @GET(Api.MATHDROID_END_POINT_DEATH)
    Call<List<Death>> getGlobalDeath();

    //kawal
    @GET(Api.KAWAL_END_POINT_IND_PROV)
    Call<List<Provinsi>> getKawalProvinsi();

    @GET(Api.KAWAL_END_POINT_IND)
    Call<List<CountryModel>> getKawalIndo();

    //indoMathdroid
    @GET(Api.INDO_BASE_URL_ID)
    Call<CountryModel> getIndo();
}
