package com.example.pyeonyook_fe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_major;

    private TextView tv_stuNum;
    private ImageButton btn_pfEdit;
    private ImageView iv_profile;
    private Button btn_changeEmail;
    private Button btn_changePw;
    private Button btn_notification;
    private Button btn_keyword;
    private Button btn_logout;
    private Button btn_deleteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_name = findViewById(R.id.tv_name);
        tv_major = findViewById(R.id.tv_major);

        tv_stuNum = findViewById(R.id.tv_stuNum);
        btn_pfEdit = findViewById(R.id.btn_pfedit);
        iv_profile = findViewById(R.id.iv_profile);

        btn_changeEmail = findViewById(R.id.btn_changeEmail);
        btn_changePw = findViewById(R.id.btn_changePw);
        btn_notification = findViewById(R.id.btn_notification);
        btn_keyword = findViewById(R.id.btn_keyword);
        btn_logout = findViewById(R.id.btn_logout);
        btn_deleteID = findViewById(R.id.btn_deleteID);

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
        btn_pfEdit.setOnClickListener(v -> {
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

        //회원탈퇴 버튼 클릭
        btn_deleteID.setOnClickListener(v -> {
            Intent modifyIntent = new Intent(Profile.this, Setting_DeleteID.class);
            startActivity(modifyIntent);
        });

        //로그아웃 버튼 클릭
        btn_logout.setOnClickListener(v -> {
            Intent modifyIntent = new Intent(Profile.this, Login.class);
            startActivity(modifyIntent);
        });
      
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_profile);
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
                return true;
            } else if (itemId == R.id.navigation_notification) {
                item.setIcon(R.drawable.ic_menu_notification_active);
                Intent intents = new Intent(this, NoticeActivity.class);
                startActivity(intents);
                finish();
                return true;
            }
            return false;
        });

    }
}

