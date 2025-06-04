package com.example.pyeonyook_fe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class School_official extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_official);

        TextView tabSchoolbus = findViewById(R.id.tabSchoolbus);
        TextView tabCampus = findViewById(R.id.tabCampus);
        TextView tabOfficial = findViewById(R.id.tabOfficial);

        tabSchoolbus.setOnClickListener(v -> {
            Intent intent = new Intent(School_official.this, Schoolbus.class);
            startActivity(intent);
            finish();
        });
        tabCampus.setOnClickListener(v -> {
            Intent intent = new Intent(School_official.this, SYU_more.class);
            startActivity(intent);
            finish();
        });

        // 여기서 radiusInPx 선언/계산 없이 바로 숫자!
        int cornerPx = 30; // 원하는 만큼 px 단위로 (보통 20~30px 적당)

        // ===== SU-Talk =====
        ImageView ivSutalk = findViewById(R.id.sutalk_img);
        ivSutalk.setOnClickListener(v -> {
            String packageName = "com.nexmotion.syu.syu_talk";
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                String url = "https://play.google.com/store/apps/details?id=" + packageName + "&hl=ko";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        // ===== 삼육도서관 =====
        ImageView ivSulib = findViewById(R.id.syulib_img);
        ivSulib.setOnClickListener(v -> {
            String packageName = "mmm.slpck.sahmyook";
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                String url = "https://play.google.com/store/apps/details?id=" + packageName + "&hl=ko";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        // ===== LMS =====
        ImageView ivLms = findViewById(R.id.lms_img);
        ivLms.setOnClickListener(v -> {
            String packageName = "kr.co.imaxsoft.hellolms";
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage(packageName);
            if (launchIntent != null) {
                startActivity(launchIntent);
            } else {
                String url = "https://play.google.com/store/apps/details?id=" + packageName + "&hl=ko";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                item.setIcon(R.drawable.ic_menu_home_active);
                Intent intents = new Intent(this, MainActivity.class);
                intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intents);
                finish();
                return true;
            } else if (itemId == R.id.navigation_profile) {
                item.setIcon(R.drawable.ic_menu_person_active);
                Intent intents = new Intent(this, Profile.class);
                startActivity(intents);
                return true;
            }else if (itemId == R.id.navigation_calendar) {
                item.setIcon(R.drawable.ic_menu_calendar_active);
                Intent intents = new Intent(this, com.example.pyeonyook_fe.Calendar.class);
                intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intents);
                finish();
                return true;
            }else if (itemId == R.id.navigation_add) {
                item.setIcon(R.drawable.ic_menu_add_active);

            } else if (itemId == R.id.navigation_notification) {
                item.setIcon(R.drawable.ic_menu_notification_active);
                Intent intents = new Intent(this, NoticeActivity.class);
                startActivity(intents);
                finish();
                return true;
            }
            return false;
        });
    }
}
