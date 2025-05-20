package com.example.pyeonyook_fe;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private NavigationBarView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottom_nav_view);
        bottomNavView.setOnItemSelectedListener(this);
        
        // 기본으로 홈 화면 표시
        if (savedInstanceState == null) {
            bottomNavView.setSelectedItemId(R.id.navigation_home);
            updateMenuIcons(R.id.navigation_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        int itemId = item.getItemId();

        // 아이콘 상태 갱신
        updateMenuIcons(itemId);

        if (itemId == R.id.navigation_home) {
            // TODO: HomeFragment 구현 후 교체
            // selectedFragment = new HomeFragment();
        } else if (itemId == R.id.navigation_calendar) {
            // TODO: ScheduleFragment 구현 후 교체
            // selectedFragment = new ScheduleFragment();
        } else if (itemId == R.id.navigation_add) {
            // TODO: AddFragment 구현 후 교체
            // selectedFragment = new AddFragment();
        } else if (itemId == R.id.navigation_notification) {
            // TODO: NotificationsFragment 구현 후 교체
            // selectedFragment = new NotificationsFragment();
        } else if (itemId == R.id.navigation_profile) {
            // TODO: ProfileFragment 구현 후 교체
            // selectedFragment = new ProfileFragment();
        }

        if (selectedFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();
            return true;
        }
        return true;
    }

    private void updateMenuIcons(int selectedItemId) {
    if (bottomNavView.getMenu() == null) return;

    for (int i = 0; i < bottomNavView.getMenu().size(); i++) {
        MenuItem item = bottomNavView.getMenu().getItem(i);
        String resName = getResources().getResourceEntryName(item.getItemId()); // ex: navigation_add
        String iconName = "ic_menu_" + resName.replace("navigation_", "");
        if (item.getItemId() == selectedItemId) {
            iconName += "_active";
        }
        int iconResId = getResources().getIdentifier(iconName, "drawable", getPackageName());
        if (iconResId != 0) {
            item.setIcon(iconResId);
        }
    }
}


    // private void updateMenuIcons(int selectedItemId) {
    //     if (bottomNavView.getMenu() == null) return;

    //     for (int i = 0; i < bottomNavView.getMenu().size(); i++) {
    //         MenuItem item = bottomNavView.getMenu().getItem(i);
    //         String resName = getResources().getResourceEntryName(item.getItemId()); // ex: navigation_home
    //         String iconName = "ic_menu_" + resName.replace("navigation_", "");
    //         if (item.getItemId() == selectedItemId) {
    //             iconName += "_active";
    //         }
    //         int iconResId = getResources().getIdentifier(iconName, "drawable", getPackageName());
    //         if (iconResId != 0) {
    //             item.setIcon(iconResId);
    //         }
    //     }
    // }
}package com.example.pyeonyook_fe;

public class MainActivity {
}
