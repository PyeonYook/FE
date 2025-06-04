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
    private Button btn_changeEmail;
    private Button btn_changePw;
    private Button btn_notification;
    private Button btn_keyword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // 바텀 네비게이션 설정 (람다 방식!)
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // 현재 페이지 무반응
                return true;
            }
            // TODO: 다른 메뉴 추가
            return false;
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);

        // 뷰 연결

        tv_name = findViewById(R.id.tv_name);
        tv_major = findViewById(R.id.tv_major);

        tv_stuNum = findViewById(R.id.tv_stuNum);
        btn_pfModi = findViewById(R.id.btn_pfModi);
        iv_profile = findViewById(R.id.iv_profile);

        btn_changeEmail = findViewById(R.id.btn_changeEmail);
        btn_changePw = findViewById(R.id.btn_changePw);
        btn_notification = findViewById(R.id.btn_notification);
        btn_keyword = findViewById(R.id.btn_keyword);

        // Intent로 값 세팅
        Intent intent = getIntent();

        String str_name = intent.getStringExtra("str_name");
        String str_major = intent.getStringExtra("str_major");

        String str_stuNum = intent.getStringExtra("str_stuNum");
        String imageUriString = intent.getStringExtra("imageUri");


        tv_name.setText("이름: " + (str_name != null ? str_name : ""));
        tv_major.setText("학과: " + (str_major != null ? str_major : ""));

        tv_stuNum.setText("학번: " + (str_stuNum != null ? str_stuNum : ""));

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            iv_profile.setImageURI(imageUri);
        }

        // 프로필 수정 버튼 클릭
        btn_pfModi.setOnClickListener(v -> {
            Intent modifyIntent = new Intent(Profile.this, ProfileModify.class);
            startActivity(modifyIntent);
        });

        // 비밀번호 변경 클릭
        btn_changePw.setOnClickListener(v -> {
            Intent modifyIntent = new Intent(Profile.this, Setting_ChangePw.class);
            startActivity(modifyIntent);
        });

        // 이메일 변경 클릭
        btn_changeEmail.setOnClickListener(v -> {
            Intent modifyIntent = new Intent(Profile.this, Setting_ChangeEmail.class);
            startActivity(modifyIntent);
        });

        //키워드 설정 버튼 클릭
        btn_keyword.setOnClickListener(v -> {
            Intent modifyIntent = new Intent(Profile.this, Setting_Keywords.class);
            startActivity(modifyIntent);
        });

        //알림설정 버튼 클릭
        btn_notification.setOnClickListener(v -> {
            Intent modifyIntent = new Intent(Profile.this, Setting_Notification.class);
            startActivity(modifyIntent);
        });

    }
}
