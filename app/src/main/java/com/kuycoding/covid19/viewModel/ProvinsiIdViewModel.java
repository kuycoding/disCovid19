package com.kuycoding.covid19.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuycoding.covid19.api.Api;
import com.kuycoding.covid19.api.ApiEndPoint;
import com.kuycoding.covid19.api.ApiService;
import com.kuycoding.covid19.model.provinsi.Provinsi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProvinsiIdViewModel extends ViewModel {
    private static final String TAG = "tag";
    private MutableLiveData<List<Provinsi>> listProvinsi = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoad = new MutableLiveData<>();

    public void setListProvinsi() {
        Retrofit retrofit = ApiService.getRetrofitServiceKawalCovid();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);
        Call<List<Provinsi>> call = apiEndPoint.getKawalProvinsi();
        call.enqueue(new Callback<List<Provinsi>>() {
            @Override
            public void onResponse(Call<List<Provinsi>> call, Response<List<Provinsi>> response) {
                if (response.isSuccessful()) {
                    List<Provinsi> list = null;
                    if (response.body() != null) {
                        list = response.body();
                    }
                    listProvinsi.postValue(list);
                    setLoad();
                    Log.d(TAG, "onResponse ada: " );
                }
            }

            @Override
            public void onFailure(Call<List<Provinsi>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setLoad() {
        isLoad.postValue(false);
    }

    public MutableLiveData<List<Provinsi>> getListProvinsi() {
        return listProvinsi;
    }
    public MutableLiveData<Boolean> getIsLoad() {
        return isLoad;
    }
}
