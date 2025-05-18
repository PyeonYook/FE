package com.example.pyeonyook_fe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private TextView tv_nickname;
    private TextView tv_name;
    private TextView tv_major;
    private TextView tv_grade;
    private TextView tv_stuNum;
    private Button btn_pfModi;
    private Button btn_setting;
    private ImageView iv_profile;
    private boolean imageLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_nickname = findViewById(R.id.tv_nickname);
        tv_name = findViewById(R.id.tv_name);
        tv_major = findViewById(R.id.tv_major);
        tv_grade = findViewById(R.id.tv_grade);
        tv_stuNum = findViewById(R.id.tv_stuNum);

        Intent intent = getIntent();
        String str_nickname = intent.getStringExtra("str_nickname");
        String str_name = intent.getStringExtra("str_name");
        String str_major = intent.getStringExtra("str_major");
        String str_grade = intent.getStringExtra("str_grade");
        String str_stuNum = intent.getStringExtra("str_stuNum");

        tv_nickname.setText("별명: " + str_nickname);
        tv_name.setText("이름: " + str_name);
        tv_major.setText("학과: " + str_major);
        tv_grade.setText("학년: " + str_grade);
        tv_stuNum.setText("학번: " + str_stuNum);

        btn_pfModi = findViewById(R.id.btn_pfModi);
        btn_pfModi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this , ProfileModify.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String imageUriString = intent.getStringExtra("imageUri");

        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            ImageView ivProfile = findViewById(R.id.iv_profile);
            ivProfile.setImageURI(imageUri);
        }
    }
}