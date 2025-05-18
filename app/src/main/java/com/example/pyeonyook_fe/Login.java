package com.example.pyeonyook_fe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // SignUp_Link TextView 연결
        TextView signUpLink = findViewById(R.id.SignUp_Link);

        // 클릭 리스너 설정
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SignUp 액티비티로 이동
                Intent intent;
                intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}