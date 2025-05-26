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
import com.example.pyeonyook_fe.api.SignupRequest;
import com.example.pyeonyook_fe.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText;
    private Button signupButton;
    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // UI 요소 초기화
        nameEditText = findViewById(R.id.signup_Name);
        emailEditText = findViewById(R.id.signup_Email);
        passwordEditText = findViewById(R.id.signup_Password);
        signupButton = findViewById(R.id.signup_Button);
        loginLink = findViewById(R.id.Login_Link);

        // 회원가입 버튼 클릭 이벤트
        signupButton.setOnClickListener(view -> signup());

        // 로그인 링크 클릭 이벤트
        loginLink.setOnClickListener(view -> {
            // 로그인 화면으로 이동
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });
    }

    private void signup() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // 입력값 유효성 검사
        if (!isValidInput(name, email, password)) {
            return;
        }

        // SignupRequest 객체 생성
        SignupRequest signupRequest = new SignupRequest(name, email, password);

        // Retrofit을 사용한 API 요청
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        Call<Void> call = apiService.createUser(signupRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // 회원가입 성공 처리
                    Toast.makeText(Signup.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                    finish(); // 현재 화면 종료
                } else {
                    // 서버로부터의 실패 응답 처리
                    Log.e("Signup", "회원가입 실패 코드: " + response.code());
                    Toast.makeText(Signup.this, "회원가입 실패! (코드: " + response.code() + ")", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // 네트워크 에러 또는 API 호출 실패
                Log.e("Signup", "onFailure: " + t.getMessage(), t);
                Toast.makeText(Signup.this, "서버와 연결되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidInput(String name, String email, String password) {
        // 이름 검사
        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("이름을 입력해주세요.");
            return false;
        }

        // 이메일 형식 검사
        if (TextUtils.isEmpty(email) || !email.contains("@")) {
            emailEditText.setError("유효한 이메일 주소를 입력해주세요.");
            return false;
        }

        // 비밀번호 검사
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordEditText.setError("비밀번호는 6자 이상이어야 합니다.");
            return false;
        }

        return true; // 모든 유효성 검사가 통과된 경우
    }
}
