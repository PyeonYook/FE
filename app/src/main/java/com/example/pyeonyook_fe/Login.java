package com.example.pyeonyook_fe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pyeonyook_fe.api.AppSession;
import com.example.pyeonyook_fe.api.AuthApi;
import com.example.pyeonyook_fe.api.IdTokenRequest;
import com.example.pyeonyook_fe.api.LoginResponse;
import com.example.pyeonyook_fe.api.RetrofitClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
                            //로그인 성공시 파이어베이스 ID토큰 획득
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                user.getIdToken(true).addOnCompleteListener(tokenTask -> {
                                    if (tokenTask.isSuccessful()) {
                                        String idToken = tokenTask.getResult().getToken();

                                        //서버에 토큰 전송
                                        String BASE_URL = "http://10.0.2.2:8080/";
                                        AuthApi api = RetrofitClient.getClient(BASE_URL).create(AuthApi.class);
                                        IdTokenRequest req = new IdTokenRequest(idToken);

                                        Call<LoginResponse> call = api.loginWithFirebase(req);
                                        call.enqueue(new Callback<LoginResponse>() {
                                            @Override
                                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                                if (response.isSuccessful() && response.body() != null) {
                                                    //서버에서 회원가입/로그인
                                                    LoginResponse userInfo = response.body();
                                                    Toast.makeText(Login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                                    AppSession.setIdToken(idToken);

                                                    //MainActivity 전환
                                                    startActivity(new Intent(Login.this, MainActivity.class));
                                                    finish();
                                                } else {
                                                    Toast.makeText(Login.this, "서버 로그인 처리 실패", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                                Toast.makeText(Login.this, "네트워크/서버 오류", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } else {
                                        Toast.makeText(this, "Token 획득 실패", Toast.LENGTH_SHORT).show();
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

}
