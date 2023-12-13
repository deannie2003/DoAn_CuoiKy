package com.example.doanck.Model;

public class Token {
    private static String token="";
    public static String getToken() {
        return token;
    }

    public static void SetToken(String Token){
        token = Token;
    }
}
