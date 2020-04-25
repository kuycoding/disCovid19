package com.kuycoding.covid19.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuycoding.covid19.api.ApiEndPoint;
import com.kuycoding.covid19.api.ApiService;
import com.kuycoding.covid19.model.GlobalModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GlobalViewModel extends ViewModel {
    private MutableLiveData<GlobalModel> mutableLiveData = new MutableLiveData<>();

    public void setGlobalData() {
        Retrofit retrofit = ApiService.getRetrofitService();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);
        Call<GlobalModel> call = apiEndPoint.getSummaryGlobal();
        call.enqueue(new Callback<GlobalModel>() {
            @Override
            public void onResponse(Call<GlobalModel> call, Response<GlobalModel> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GlobalModel> call, Throwable t) {

            }
        });
    }
    public LiveData<GlobalModel> getGlobalData() {
        return mutableLiveData;
    }
}
