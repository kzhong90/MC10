package com.kzhong.mc10.activity;

import android.support.annotation.NonNull;

import com.kzhong.mc10.intefaces.MC10Api;
import com.kzhong.mc10.intefaces.StudiesListView;
import com.kzhong.mc10.model.Studies;
import com.kzhong.mc10.network.BasicAuthInterceptor;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudiesListPresenter {

    private static final String BASE_URL = "https://qa.mc10cloud.com/api/v1/";

    private StudiesListView view;
    private MC10Api service;

    private String studiesListURL;

    StudiesListPresenter(StudiesListView view, String id, String accountId, String accessToken) {
        this.view = view;

        studiesListURL = "https://qa.mc10cloud.com/api/v1/accounts/"+accountId+"/studies";

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(id, accessToken))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(MC10Api.class);
    }

    public void getStudiesList() {

        Call<ArrayList<Studies>> call = service.getStudiesCall(studiesListURL);

        call.enqueue(new Callback<ArrayList<Studies>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Studies>> call, @NonNull Response<ArrayList<Studies>> response) {

                ArrayList<Studies> studies = response.body();

                if(response.isSuccessful()) {
                    if(studies!=null) {
                        view.onResponseSuccess(studies);
                    } else {
                        view.onResponseFailure("Studies response is null");
                    }
                } else {
                    view.onResponseFailure("Studies call response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Studies>> call, @NonNull Throwable t) {
                view.onResponseFailure("Get studies call failed.");
            }
        });
    }

    public void logOut() {

        Call<ResponseBody> call = service.logOut();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if(response.isSuccessful()) {
                    view.onLogOut("Bluetooth not enabled, logging out and returning to login.");
                } else {
                    view.onResponseFailure("Log out response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                view.onResponseFailure("Log out call failed.");
            }
        });
    }
}
