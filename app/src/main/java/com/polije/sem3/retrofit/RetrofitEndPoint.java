package com.polije.sem3.retrofit;

import com.polije.sem3.response.UserResponse;
import com.polije.sem3.response.WisataResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitEndPoint {
    @FormUrlEncoded
    @POST("login.php")
    Call<UserResponse> login(
            @Field("userid") String userid,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<UserResponse> register(
            @Field("username") String username,
            @Field("email") String email,
            @Field("fullname") String fullName,
            @Field("password") String password
        );

    @GET("data_wisata.php")
    Call<WisataResponse> wisata(
    );

}
