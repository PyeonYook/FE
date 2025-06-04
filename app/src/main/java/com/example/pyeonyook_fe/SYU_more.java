package com.example.pyeonyook_fe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SYU_more extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syu_more);


        TextView tabSchoolbus = findViewById(R.id.tabSchoolbus);
        TextView tabCampus = findViewById(R.id.tabCampus);
        TextView tabOfficial = findViewById(R.id.tabOfficial);

        tabSchoolbus.setOnClickListener(v -> {
            Intent intent = new Intent(SYU_more.this, Schoolbus.class);
            startActivity(intent);
            finish();
        });
        /*tabOfficial.setOnClickListener(v -> {
            Intent intent = new Intent(SYU_more.this, School_official.class);
            startActivity(intent);
            finish();
        });*/

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navigation_add);
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
