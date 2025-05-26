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

            mAuth.signInWithEmailAndPassword(email, pw)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // 로그인 성공
                            startActivity(new Intent(this, MainActivity.class));
                        } else {
                            // 로그인 실패
                            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}

