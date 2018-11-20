package com.kzhong.mc10.activity;

import android.support.annotation.NonNull;

import com.kzhong.mc10.intefaces.LogInInView;
import com.kzhong.mc10.intefaces.MC10Api;
import com.kzhong.mc10.model.UserAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInPresenter {

    private LogInInView view;

    private static final String BASE_URL = "https://qa.mc10cloud.com/api/v1/";

    LogInPresenter(LogInInView view) {
        this.view = view;
    }

    public void onButtonClicked(String username, String password) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MC10Api service = retrofit.create(MC10Api.class);

        Call<UserAccount> call = service.logInWithCredentials(username, password);

        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(@NonNull Call<UserAccount> call, @NonNull Response<UserAccount> response) {

                if(response.isSuccessful() && response.body() != null) {

                    UserAccount userAccount = response.body();

                    if(userAccount!=null) {
                        view.onResponseSuccess(userAccount);
                    } else {
                        view.onResponseFailure("Response is null");
                    }

                } else {
                    view.onResponseFailure("Response not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserAccount> call, @NonNull Throwable t) {
                view.onResponseFailure("Log in call failed.");
            }
        });
    }


}
