package com.example.doanck.API;

import com.example.doanck.Model.AssetToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {

    //Link API : https://uiot.ixxc.dev/auth/realms/master/protocol/openid-connect/token

    @FormUrlEncoded
    @POST("/auth/realms/master/protocol/openid-connect/token")

   Call<AssetToken>
}
