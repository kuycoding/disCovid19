package com.kuycoding.covid19.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kuycoding.covid19.api.ApiEndPoint;
import com.kuycoding.covid19.api.ApiService;
import com.kuycoding.covid19.model.GlobalDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GlobalAllDataViewModel extends ViewModel {
    private MutableLiveData<List<GlobalDataModel>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoad = new MutableLiveData<>();

    public void setGlobalData() {
        Retrofit retrofit = ApiService.getRetrofitService();
        ApiEndPoint apiEndPoint = retrofit.create(ApiEndPoint.class);
        Call<List<GlobalDataModel>> call = apiEndPoint.getGlobalData();
        call.enqueue(new Callback<List<GlobalDataModel>>() {
            @Override
            public void onResponse(Call<List<GlobalDataModel>> call, Response<List<GlobalDataModel>> response) {
                if (response.body() != null) {
                    List<GlobalDataModel> globalDataModelList = null;
                    if (response.body() != null) {
                        globalDataModelList = response.body();
                    }
//                    mutableLiveData.setValue(response.body());
                    mutableLiveData.postValue(globalDataModelList);
                    setLoad();
                }
            }

            @Override
            public void onFailure(Call<List<GlobalDataModel>> call, Throwable t) {

            }
        });
    }

    private void setLoad() {
        isLoad.postValue(false);
    }

    public MutableLiveData<Boolean> getIsLoad() {
        return isLoad;
    }

    public LiveData<List<GlobalDataModel>> getGlobalData() {
        return mutableLiveData;
    }

}
