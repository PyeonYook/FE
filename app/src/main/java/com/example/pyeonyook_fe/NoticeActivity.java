package com.example.pyeonyook_fe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.pyeonyook_fe.api.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoticeAdapter noticeAdapter;
    private List<Notice> noticeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        recyclerView = findViewById(R.id.rvNotice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noticeAdapter = new NoticeAdapter(noticeList);    // 빈 리스트로 초기화
        recyclerView.setAdapter(noticeAdapter);

        // 실제 네트워크 통신 (Retrofit)
        getNoticesWithToken(AppSession.getIdToken());
    }

    // 서버에 공지요청 (토큰 포함)
    private void getNoticesWithToken(String idToken) {
        String BASE_URL = "http://10.0.2.2:8080/"; // 에뮬레이터 기준
        NoticeApi api = RetrofitClient.getClient(BASE_URL).create(NoticeApi.class);

        Call<List<Notice>> call = api.getAllNotices("Bearer " + idToken);
        call.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API", "공지개수: " + response.body().size());
                    // response.body() 활용(리사이클러뷰 등)
                } else {
                    Log.e("API", "응답오류: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Notice>> call, Throwable t) {
                Log.e("API", "네트워크오류", t);
            }
        });
    }
}