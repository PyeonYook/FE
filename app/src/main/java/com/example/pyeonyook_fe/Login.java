package com.example.pyeonyook_fe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.pyeonyook_fe.api.Notice;
import com.example.pyeonyook_fe.api.NoticeApi;
import com.example.pyeonyook_fe.api.RetrofitClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.Login_email);
        editTextPassword = findViewById(R.id.Login_password);

        Button loginBtn = findViewById(R.id.Login_Button);
        loginBtn.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String pw = editTextPassword.getText().toString();


            if (email.isEmpty() || pw.isEmpty()) {
                Toast.makeText(this, "이메일과 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // 1. 로그인 성공시 파이어베이스 ID 토큰 획득!
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                user.getIdToken(true).addOnCompleteListener(tokenTask -> {
                                    if (tokenTask.isSuccessful()) {
                                        String idToken = tokenTask.getResult().getToken();
                                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();

                                        // 2. 서버로 API 요청 (예시: 공지 전체 가져오기)
                                        getNoticesWithToken(idToken);

                                        // 화면 이동(원하면 MainActivity로)
                                        startActivity(new Intent(this, MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(this, "Token 획득 실패!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // 회원가입으로 이동 링크
        TextView signUpLink = findViewById(R.id.SignUp_Link);
        signUpLink.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
        });
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
