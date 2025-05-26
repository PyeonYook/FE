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
            String stdno = editTextName.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // 성공
                            Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                            finish(); // 로그인 화면으로 이동 등
                        } else {
                            // 실패
                            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
