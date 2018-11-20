package com.kzhong.mc10.intefaces;

import com.kzhong.mc10.model.Studies;
import com.kzhong.mc10.model.UserAccount;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface MC10Api {

    @Headers({
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("user/login/email")
    Call<UserAccount> logInWithCredentials(@Field("email") String username,
                                           @Field("password") String password);

    @Headers({
            "Accept: application/json"
    })
    @GET
    Call<ArrayList<Studies>> getStudiesCall(@Url String fullUrl);

    @Headers({
            "Content-Type: text/plain",
            "Accept: application/json"
    })
    @POST("users/logout")
    Call<ResponseBody> logOut();
}
