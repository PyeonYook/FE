package com.example.pyeonyook_fe.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:3000"; // 로컬 서버 URL
    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL) // API 기본 URL
                    .addConverterFactory(GsonConverterFactory.create()) // JSON 컨버터 추가
                    .build();
        }
        return retrofit;
    }
}
