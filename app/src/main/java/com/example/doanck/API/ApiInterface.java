package com.example.doanck.API;


import com.example.doanck.Model.AssetToken;
import com.example.doanck.Model.Datapoint;
import com.example.doanck.Model.MapModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
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
    Call<MapModel> getMap();
    @GET("api/master/asset/{assetID}")
    Call<Object> getDevices(@Path("assetID") String assetID, @Header("Authorization") String auth);

    @POST("/api/master/asset/datapoint/{assetId}/attribute/{attributeName}")
    Call<List<Datapoint>> getDataPoints(
            @Header("accept") String accept,
            @Header("Authorization") String auth,
            @Header("Content-Type") String contentType,
            @Path("assetId") String assetId,
            @Path("attributeName") String attributeName,
            @Body RequestBody body
    );

}
