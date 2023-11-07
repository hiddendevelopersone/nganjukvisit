package com.polije.sem3.retrofit;

import com.polije.sem3.response.EventResponse;
import com.polije.sem3.response.FavoritWisataResponse;
import com.polije.sem3.response.KulinerResponse;
import com.polije.sem3.response.PenginapanResponse;
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

    @GET("data_event.php")
    Call<EventResponse> event(
    );

    @GET("data_penginapan.php")
    Call<PenginapanResponse> penginapan(
    );

    @GET("data_kuliner.php")
    Call<KulinerResponse> kuliner(
    );

    @FormUrlEncoded
    @POST("favorit_wisata.php")
    Call<FavoritWisataResponse> favwisata(
        @Field("idpengguna") String iduser
    );
}
