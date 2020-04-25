package com.kuycoding.covid19.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuycoding.covid19.api.ApiEndPoint;
import com.kuycoding.covid19.api.ApiService;
import com.kuycoding.covid19.model.Recovered;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecoveredViewModel extends ViewModel {

    private MutableLiveData<List<Recovered>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoad = new MutableLiveData<>();

    public void setRecovered() {
        Retrofit retrofit = ApiService.getRetrofitService();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);
        Call<List<Recovered>> call = apiEndPoint.getGlobalRecovered();
        call.enqueue(new Callback<List<Recovered>>() {
            @Override
            public void onResponse(Call<List<Recovered>> call, Response<List<Recovered>> response) {
                if (response.isSuccessful()) {
                    List<Recovered> recovereds = null;
                    if (response.body() != null) {
                        recovereds = response.body();
                    }
                    mutableLiveData.postValue(recovereds);
                    setLoad();
                }
            }

            @Override
            public void onFailure(Call<List<Recovered>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Recovered>> getRecovered() {
        return mutableLiveData;
    }

    private void setLoad() {
        isLoad.postValue(false);
    }

    public MutableLiveData<Boolean> getLoad() {
        return isLoad;
    }
}
