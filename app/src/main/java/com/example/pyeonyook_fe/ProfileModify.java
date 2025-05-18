package com.example.pyeonyook_fe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ProfileModify extends AppCompatActivity {

    private EditText et_nickname, et_name, et_major, et_grade, et_stuNum;
    private String str_nickname;
    private String str_name;
    private String str_major;
    private String str_grade;
    private String str_stuNum;
    private Button btn_save;
    private static final int IMAGE_PICK_CODE = 1000;
    Uri selctedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_modify);

        Button btn_selectImage = findViewById(R.id.btn_selectImage);
        btn_selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_PICK_CODE);
            }
        });

        et_nickname = findViewById(R.id.et_nickname);
        et_name = findViewById(R.id.et_name);
        et_major = findViewById(R.id.et_major);
        et_grade = findViewById(R.id.et_grade);
        et_stuNum = findViewById(R.id.et_stuNum);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_nickname = et_nickname.getText().toString();
                str_name = et_name.getText().toString();
                str_major = et_major.getText().toString();
                str_grade = et_grade.getText().toString();
                str_stuNum = et_stuNum.getText().toString();

                Intent intent = new Intent(ProfileModify.this , Profile.class);
                intent.putExtra("str_nickname",str_nickname);
                intent.putExtra("str_name",str_name);
                intent.putExtra("str_major",str_major);
                intent.putExtra("str_grade",str_grade);
                intent.putExtra("str_stuNum",str_stuNum);

                if (selctedImageUri != null) {
                    intent.putExtra("imageUri", selctedImageUri.toString());
                }

                startActivity(intent);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data != null) {
            selctedImageUri = data.getData();

            ImageView iv_preview = findViewById(R.id.iv_preview);
            iv_preview.setImageURI(selctedImageUri);
        }
    }
}