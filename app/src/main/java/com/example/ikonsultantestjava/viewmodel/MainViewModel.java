package com.example.ikonsultantestjava.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ikonsultantestjava.api.ApiHelper;
import com.example.ikonsultantestjava.model.Post;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class MainViewModel extends ViewModel {

    public MutableLiveData<List<Post>> posts = new MutableLiveData<>();

    public void fetchData() {
        try {
            Response<List<Post>> request = new ApiHelper().getService().request().execute();
            if (request.isSuccessful()) {
                posts.postValue(request.body());
            } else {
                Log.d("MainViewModel", "fetchData: " + request);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
