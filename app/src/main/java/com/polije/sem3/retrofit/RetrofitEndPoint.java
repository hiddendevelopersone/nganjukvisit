package com.polije.sem3.retrofit;

import com.polije.sem3.response.DetailEventResponse;
import com.polije.sem3.response.DetailWisataResponse;
import com.polije.sem3.response.EventResponse;
import com.polije.sem3.response.FavoritKulinerResponse;
import com.polije.sem3.response.FavoritPenginapanResponse;
import com.polije.sem3.response.FavoritWisataResponse;
import com.polije.sem3.response.KulinerResponse;
import com.polije.sem3.response.PenginapanResponse;
import com.polije.sem3.response.UlasanKirimResponse;
import com.polije.sem3.response.UlasanResponse;
import com.polije.sem3.response.UserResponse;
import com.polije.sem3.response.WisataResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("populer_penginapan.php")
    Call<PenginapanResponse> penginapanpopuler(
    );

    @GET("populer_wisata.php")
    Call<WisataResponse> wisatapopuler(
    );

    @GET("populer_kuliner.php")
    Call<KulinerResponse> kulinerpopuler(
    );

    @GET("detailed_data_wisata.php")
    Call<DetailWisataResponse> detailwisata(
            @Query("id_selected") String id_selected
    );

    @GET("data_ulasan.php")
    Call<UlasanResponse> ulasan(
            @Query("id_selected") String id_selected
    );

    @FormUrlEncoded
    @POST("tambah_ulasan.php")
    Call<UlasanKirimResponse> kirimulasan(
            @Field("idPengguna") String idPengguna,
            @Field("nama_pengguna") String namaPengguna,
            @Field("comment") String comment,
            @Field("wisataid") String idWisata
    );

    @GET("detailed_data_event.php")
    Call<DetailEventResponse> detailevent(
            @Query("id_selected") String idSelected
    );

    @FormUrlEncoded
    @POST("favorit_wisata.php")
    Call<FavoritWisataResponse> favwisata(
        @Field("idpengguna") String iduser
    );

    @FormUrlEncoded
    @POST("favorit_penginapan.php")
    Call<FavoritPenginapanResponse> favpenginapan(
            @Field("idpengguna") String iduser
    );

    @FormUrlEncoded
    @POST("favorit_kuliner.php")
    Call<FavoritKulinerResponse> favkuliner(
            @Field("idpengguna") String iduser
    );
}
