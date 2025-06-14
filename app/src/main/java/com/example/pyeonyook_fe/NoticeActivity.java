package com.example.pyeonyook_fe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pyeonyook_fe.api.AppSession;
import com.example.pyeonyook_fe.api.Notice;
import com.example.pyeonyook_fe.api.NoticeApi;
import com.example.pyeonyook_fe.api.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

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
        noticeAdapter = new NoticeAdapter(noticeList);
        recyclerView.setAdapter(noticeAdapter);

        getNoticesWithToken(AppSession.getIdToken());
        setupBottomNavigation();
    }

    private void getNoticesWithToken(String idToken) {
        String BASE_URL = "http://10.0.2.2:8080/"; // 에뮬레이터 기준
        NoticeApi api = RetrofitClient.getClient(BASE_URL).create(NoticeApi.class);

        Call<List<Notice>> call = api.getAllNotices("Bearer " + idToken);
        call.enqueue(new Callback<List<Notice>>() {
            @Override
            public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API", "공지개수: " + response.body().size());
                    noticeList.clear();
                    noticeList.addAll(response.body());
                    noticeAdapter.notifyDataSetChanged();
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

    private void setupBottomNavigation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_notification);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                item.setIcon(R.drawable.ic_menu_home_active);
                Intent intents = new Intent(this, MainActivity.class);
                intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intents);
                finish();
                return true;
            } else if (itemId == R.id.navigation_profile) {
                item.setIcon(R.drawable.ic_menu_person_active);
                Intent intents = new Intent(this, Profile.class);
                startActivity(intents);
                return true;
            }else if (itemId == R.id.navigation_calendar) {
                item.setIcon(R.drawable.ic_menu_calendar_active);
                Intent intents = new Intent(this, com.example.pyeonyook_fe.Calendar.class);
                intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intents);
                finish();
                return true;
            }else if (itemId == R.id.navigation_add) {
                item.setIcon(R.drawable.ic_menu_add_active);
                Intent intents = new Intent(this, SYU_more.class);
                startActivity(intents);
                finish();
                return true;
            } else if (itemId == R.id.navigation_notification) {
                item.setIcon(R.drawable.ic_menu_notification_active);
            }
            return false;
        });

    }
}