package com.example.pyeonyook_fe.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/users/signup")  // 회원가입 API
    Call<Void> createUser(@Body SignupRequest signupRequest);

    @POST("/users/login")   // 로그인 API
    Call<Void> login(@Body LoginRequest loginRequest);

    Call<Void> login(String email, String password);
}
