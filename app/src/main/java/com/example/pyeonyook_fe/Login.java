package com.example.pyeonyook_fe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pyeonyook_fe.api.ApiService;
import com.example.pyeonyook_fe.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // UI 연결
        emailEditText = findViewById(R.id.Login_email);
        passwordEditText = findViewById(R.id.Login_password);
        loginButton = findViewById(R.id.Login_Button);
        signUpLink = findViewById(R.id.SignUp_Link);

        // 로그인 버튼 클릭 이벤트
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // 유효성 검사
                if (TextUtils.isEmpty(email)) {
                    emailEditText.setError("이메일을 입력해주세요");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    passwordEditText.setError("비밀번호를 입력해주세요");
                    return;
                }

                // 서버와 통신하여 로그인 처리
                login(email, password);
            }
        });

        // 회원가입 링크 클릭 이벤트
        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Signup 액티비티로 이동
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    // 로그인 요청
    private void login(String email, String password) {
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<Void> call = apiService.login(email, password);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // 로그인 성공
                    Toast.makeText(Login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    // 로그인 성공 후 Main 액티비티로 이동
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Login 액티비티 종료
                } else {
                    // 로그인 실패
                    Toast.makeText(Login.this, "로그인 실패: 이메일 혹은 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // 서버 연결 실패
                Toast.makeText(Login.this, "서버와 통신에 실패했습니다.", Toast.LENGTH_SHORT).show();
                Log.e("Login", "onFailure: ", t);
            }
        });
    }
}
