package com.example.pyeonyook_fe.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/api/auth/login")
    Call<LoginResponse> loginWithFirebase(@Body IdTokenRequest request);
}

