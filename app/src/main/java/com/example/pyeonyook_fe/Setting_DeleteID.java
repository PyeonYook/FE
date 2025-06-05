package com.example.pyeonyook_fe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Setting_DeleteID extends AppCompatActivity {

    private CheckBox cb_confirm;
    private Button btn_deleteID_fin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_delete_id);

        cb_confirm = findViewById(R.id.cb_confirm);
        btn_deleteID_fin = findViewById(R.id.btn_deleteID_fin);

        btn_deleteID_fin.setEnabled(false);
        btn_deleteID_fin.setAlpha(0.5f);

        cb_confirm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btn_deleteID_fin.setEnabled(isChecked);
            btn_deleteID_fin.setAlpha(isChecked ? 1.0f : 0.5f);
        });

        btn_deleteID_fin.setOnClickListener(v -> {
            if (cb_confirm.isChecked()) {
                //탈퇴 처리
                Toast.makeText(Setting_DeleteID.this, "회원탈퇴 처리가 진행됩니다.", Toast.LENGTH_SHORT).show();
            } else {
                //체크 안했을 때 팝업
                Toast.makeText(Setting_DeleteID.this, "탈퇴 전 유의사항을 확인해주세요", Toast.LENGTH_SHORT).show();
            }
        });

    }
}