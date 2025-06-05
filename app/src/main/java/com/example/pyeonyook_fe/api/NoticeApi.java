package com.example.pyeonyook_fe.api;

import com.example.pyeonyook_fe.NoticeActivity;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NoticeApi {
    @GET("/api/notice/all")
    Call<List<Notice>> getAllNotices(@Header("Authorization") String bearerToken);
}
