package com.example.doanck.API;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;
    private static String URL_BASE = "https://uiot.ixxc.dev/";

    public static  String Token ;
    public static OkHttpClient getUnsafeOkHttpClient() {
        String token="eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoREkwZ2hyVlJvaE5zVy1wSXpZeDBpT2lHMzNlWjJxV21sRk4wWGE1dWkwIn0..f5Llx_jH5BCa1OLJlTT38WwBHDSFOs2uJeWJJP-QEkLJB1ImhJIM-4HeMkwuPd0--qcuHmiW6lpSmt8NbKPD0JFOPI9nyHbTH6byFhBlOaYqdg-qAPSQGMeDEouuGC9vYSCd5Li8ucK_awCRv4pi7o0tpYGi8l_J-gVBdzZdO_ge_-lrRdAjYSXLI72OSe9ncZlt2IxnFoNTm3_9K5QVaHpibUk6d2YE_GhXDTHg8uuurOH5loRO1MowjKwfi7zwmoB1UlMvd58aoljaJr8saDK2zLg8OdBA";
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);

            builder.addInterceptor(chain -> {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .addHeader("Content-Type", "application/json");

                if (originalRequest.body() != null) {
                    long contentLength = originalRequest.body().contentLength();
                    requestBuilder.addHeader("Content-Length", String.valueOf(contentLength));
                }

                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            });

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Retrofit getClient() {
        OkHttpClient client = getUnsafeOkHttpClient();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
