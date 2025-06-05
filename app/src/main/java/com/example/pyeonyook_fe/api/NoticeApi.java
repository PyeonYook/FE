package com.example.pyeonyook_fe.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NoticeApi {
    @GET("/api/notice/all") //모든 공지 가져오기
    Call<List<Notice>> getAllNotices(@Header("Authorization") String bearerToken);

    @GET("/api/keyword/notices") //키워드 포함된 공지 가져오기
    Call<List<Notice>> getMyKeywordNotices(@Header("Authorization") String bearerToken);
}
