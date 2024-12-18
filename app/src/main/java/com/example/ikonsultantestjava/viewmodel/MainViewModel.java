package com.example.ikonsultantestjava.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ikonsultantestjava.api.ApiHelper;
import com.example.ikonsultantestjava.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    public MutableLiveData<List<Post>> posts = new MutableLiveData<>();

    public void fetchData() {
        Call<List<Post>> call = ApiHelper.getService().request();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    posts.postValue(response.body());
                } else {
                    Log.d("MainViewModel", "fetchData: " + response);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable throwable) {
                Log.d("MainViewModel", "fetchData: " + throwable.getLocalizedMessage());
                throwable.printStackTrace();
            }
        });
    }
}
