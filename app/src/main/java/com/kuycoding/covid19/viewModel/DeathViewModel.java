package com.kuycoding.covid19.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuycoding.covid19.api.ApiEndPoint;
import com.kuycoding.covid19.api.ApiService;
import com.kuycoding.covid19.model.Death;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DeathViewModel extends ViewModel {
    private MutableLiveData<List<Death>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoad  = new MutableLiveData<>();

    public void setDeath() {
        Retrofit retrofit = ApiService.getRetrofitService();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);
        Call<List<Death>> call = apiEndPoint.getGlobalDeath();
        call.enqueue(new Callback<List<Death>>() {
            @Override
            public void onResponse(Call<List<Death>> call, Response<List<Death>> response) {
                if (response.isSuccessful()) {
                    List<Death> deaths = null;
                    if (response.body() != null) {
                        deaths = response.body();
                    }
                    mutableLiveData.postValue(deaths);
                    setLoad();
                }
            }

            @Override
            public void onFailure(Call<List<Death>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Death>> getDeath() {
        return mutableLiveData;
    }

    public MutableLiveData<Boolean> getIsLoad() {
        return isLoad;
    }
    private void setLoad() {
        isLoad.postValue(false);
    }
}
