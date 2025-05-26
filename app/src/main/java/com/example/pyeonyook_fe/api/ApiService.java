package com.example.pyeonyook_fe.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    // 회원가입 엔드포인트 정의
    @POST("/users/signup")
    Call<Void> createUser(@Body SignupRequest signupRequest);

    // 로그인 엔드포인트 정의
    @POST("/users/login")
    Call<Void> login(@Body LoginRequest loginRequest);
}
