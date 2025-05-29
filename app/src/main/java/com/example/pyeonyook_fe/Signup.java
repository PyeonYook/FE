package com.example.pyeonyook_fe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextPassword, editTextName, editTextStdno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.signup_Email);
        editTextPassword = findViewById(R.id.signup_Password);
        editTextName = findViewById(R.id.signup_Name);
        editTextStdno = findViewById(R.id.signup_Stdno);

        Button signupBtn = findViewById(R.id.signup_Button);
        signupBtn.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String pw = editTextPassword.getText().toString();
            String name = editTextName.getText().toString();
            String stdno = editTextStdno.getText().toString(); // ← 여기 수정!

            if (email.isEmpty() || pw.isEmpty() || name.isEmpty() || stdno.isEmpty()) {
                Toast.makeText(this, "모든 정보를 입력하세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish(); // 회원가입화면 종료, 로그인화면 복귀
                        } else {
                            Toast.makeText(this, "회원가입 실패: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        // 로그인으로 이동
        TextView loginLink = findViewById(R.id.Login_Link);
        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
