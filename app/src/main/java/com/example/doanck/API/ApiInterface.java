package com.example.doanck.API;

import android.telecom.Call;

import com.example.doanck.Model.AssetToken;



public interface ApiInterface {

    //Link API : https://uiot.ixxc.dev/auth/realms/master/protocol/openid-connect/token

    @FormUrlEncoded
    @POST("/auth/realms/master/protocol/openid-connect/token")
    Call<AccessToken>
}
