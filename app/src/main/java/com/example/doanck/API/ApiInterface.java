package com.example.doanck.API;


import com.example.doanck.Model.AssetToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {

    //Link API : https://uiot.ixxc.dev/auth/realms/master/protocol/openid-connect/token

    //khởi tạo gson
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    //khởi tạo retrofit
    ApiInterface apiInterface = new Retrofit.Builder()
            .baseUrl("https://uiot.ixxc.dev/")//domain name
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiInterface.class);

    @FormUrlEncoded
    @POST("/auth/realms/master/protocol/openid-connect/token") //phan con lai cua domain name
    Call<AssetToken> authenticate(
            @Field("client_id") String clientId,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("grant_type") String grantType
    );

    @GET("/api/master/map")
    Call<Object> getMap();
    @GET("api/master/asset/{assetID}")
    Call<Object> getDevices(@Path("assetID") String assetID, @Header("Authorization") String auth);

}
