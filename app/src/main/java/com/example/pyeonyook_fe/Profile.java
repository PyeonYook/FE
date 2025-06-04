package com.example.pyeonyook_fe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {


    private TextView tv_name;
    private TextView tv_major;

    private TextView tv_stuNum;
    private ImageButton btn_pfModi;
    private ImageView iv_profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // 바텀 네비게이션 설정 (람다 방식!)
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                item.setIcon(R.drawable.ic_menu_home_active);
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.navigation_profile) {
                item.setIcon(R.drawable.ic_menu_person_active);
                return true;
            }else if (itemId == R.id.navigation_calendar) {
                item.setIcon(R.drawable.ic_menu_calendar_active);
                Intent intent = new Intent(this, com.example.pyeonyook_fe.Calendar.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }else if (itemId == R.id.navigation_add) {
                item.setIcon(R.drawable.ic_menu_add_active);
            } else if (itemId == R.id.navigation_notification) {
                item.setIcon(R.drawable.ic_menu_notification_active);
            }
            return false;


        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);

        // 뷰 연결

        tv_name = findViewById(R.id.tv_name);
        tv_major = findViewById(R.id.tv_major);

        tv_stuNum = findViewById(R.id.tv_stuNum);
        btn_pfModi = findViewById(R.id.btn_pfedit);
        iv_profile = findViewById(R.id.iv_profile);

        // Intent로 값 세팅
        Intent intent = getIntent();

        String str_name = intent.getStringExtra("str_name");
        String str_major = intent.getStringExtra("str_major");

        String str_stuNum = intent.getStringExtra("str_stuNum");
        String imageUriString = intent.getStringExtra("imageUri");


        tv_name.setText(str_name != null ? str_name : "김예찬");
        tv_major.setText(str_major != null ? str_major : "컴퓨터공학부");

        tv_stuNum.setText(str_stuNum != null ? str_stuNum : "2021100967");

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            iv_profile.setImageURI(imageUri);
        }

        // 프로필 수정 버튼 클릭
        btn_pfModi.setOnClickListener(v -> {
            Intent modifyIntent = new Intent(Profile.this, ProfileModify.class);
            startActivity(modifyIntent);
        });

    }
}
