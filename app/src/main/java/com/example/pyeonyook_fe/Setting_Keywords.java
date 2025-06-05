package com.example.pyeonyook_fe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Setting_Keywords extends AppCompatActivity {

    private EditText et_keyword;
    private Button btn_saveKeyword, btn_deleteKeyword;
    private LinearLayout keywordContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_keywords);

        et_keyword = findViewById(R.id.et_keyword);
        btn_saveKeyword = findViewById(R.id.btn_saveKeyword);
        btn_deleteKeyword = findViewById(R.id.btn_deleteKeyword);
        keywordContainer = findViewById(R.id.keyword_container);

        // 저장 버튼 클릭
        btn_saveKeyword.setOnClickListener(v -> {
            String keyword = et_keyword.getText().toString().trim();
            if (!keyword.isEmpty()) {
                addKeywordItem(keyword);
                et_keyword.setText("");
            }
        });

        // 삭제 버튼 클릭
        btn_deleteKeyword.setOnClickListener(v -> removeCheckedKeywords());
    }

    private void addKeywordItem(String keyword) {
        CheckBox checkBox = new CheckBox(this); // 기본 CheckBox 사용
        checkBox.setText(keyword);
        checkBox.setTextSize(14);
        checkBox.setTextColor(Color.BLACK);
        keywordContainer.addView(checkBox);
    }

    private void removeCheckedKeywords() {
        for (int i = keywordContainer.getChildCount() - 1; i >= 0; i--) {
            View child = keywordContainer.getChildAt(i);
            if (child instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) child;
                if (checkBox.isChecked()) {
                    keywordContainer.removeViewAt(i);
                }
            }
        }
    }
}
