package com.kuycoding.covid19.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuycoding.covid19.api.ApiEndPoint;
import com.kuycoding.covid19.api.ApiService;
import com.kuycoding.covid19.model.Confirmed;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfirmedViewModel extends ViewModel {
    private MutableLiveData<List<Confirmed>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoad = new MutableLiveData<>();

    public void setCountry() {
        Retrofit retrofit = ApiService.getRetrofitService();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);
        Call<List<Confirmed>> call = apiEndPoint.getGlobalConfirmed();
        call.enqueue(new Callback<List<Confirmed>>() {
            @Override
            public void onResponse(Call<List<Confirmed>> call, Response<List<Confirmed>> response) {
                if (response.isSuccessful()) {
                    List<Confirmed> confirmedList = null;
                    if (response.body() != null){
                        confirmedList = response.body();
                    }
                    mutableLiveData.postValue(confirmedList);
                    setLoad();
                }
            }

            @Override
            public void onFailure(Call<List<Confirmed>> call, Throwable t) {

            }
        });
    }

    private void setLoad() {
        isLoad.postValue(false);
    }

    public MutableLiveData<Boolean> getIsLoad(){
        return isLoad;
    }

    public MutableLiveData<List<Confirmed>> getCountry() {
        return mutableLiveData;
    }
}
