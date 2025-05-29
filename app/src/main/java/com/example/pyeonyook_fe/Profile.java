package com.example.pyeonyook_fe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    private TextView tv_name;
    private TextView tv_major;
    private TextView tv_stuNum;
    private ImageButton btn_pfedit;
    private Button btn_setting;
    private ImageView iv_profile;
    private boolean imageLoaded = false;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_name = findViewById(R.id.tv_name);
        tv_major = findViewById(R.id.tv_major);
        tv_stuNum = findViewById(R.id.tv_stuNum);
        iv_profile = findViewById(R.id.iv_profile);

        Intent intent = getIntent();
        String str_name = intent.getStringExtra("str_name");
        String str_major = intent.getStringExtra("str_major");
        String str_stuNum = intent.getStringExtra("str_stuNum");
        String imageUriString = intent.getStringExtra("imageUri");

        tv_name.setText(str_name);
        tv_major.setText(str_major);
        tv_stuNum.setText(str_stuNum);


        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            iv_profile.setImageURI(imageUri);
        }

        btn_pfedit = findViewById(R.id.btn_pfedit);
        btn_pfedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, ProfileModify.class);
                startActivity(intent);
            }
        });
    }
}