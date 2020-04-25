package com.kuycoding.covid19.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuycoding.covid19.api.ApiEndPoint;
import com.kuycoding.covid19.api.ApiService;
import com.kuycoding.covid19.model.CountryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CountryViewModel extends ViewModel {
    private MutableLiveData<List<CountryModel>> mutableLiveData = new MutableLiveData<>();

    public void setCountryData() {
        Retrofit retrofit = ApiService.getRetrofitServiceKawalCovid();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);
        Call<List<CountryModel>> call = apiEndPoint.getKawalIndo();
        call.enqueue(new Callback<List<CountryModel>>() {
            @Override
            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
                if (response.isSuccessful()) {
                    List<CountryModel> countryModelList = null;
                    if (response.body() != null) {
                        countryModelList = response.body();
                    }
                    mutableLiveData.postValue(countryModelList);
                }
            }

            @Override
            public void onFailure(Call<List<CountryModel>> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<List<CountryModel>> getCountryData() {
        return mutableLiveData;
    }
}
