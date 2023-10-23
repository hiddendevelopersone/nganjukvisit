package com.polije.sem3.retrofit;

import com.polije.sem3.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitEndPoint {
    @FormUrlEncoded
    @POST("login.php")
    Call<UserResponse> login(
            @Field("userid") String userid,
            @Field("password") String password
    );

}
