package com.example.pyeonyook_fe; // Use your actual package name

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

// Import RecyclerView and related classes if you choose to use it
// import androidx.recyclerview.widget.LinearLayoutManager;
// import androidx.recyclerview.widget.RecyclerView;
// import java.util.ArrayList;
// import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    // If using RecyclerView:
    // private RecyclerView recyclerViewKeywords;
    // private KeywordAdapter keywordAdapter;
    // private List<KeywordItem> keywordItemList;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Assuming your main activity layout includes the bottom navigation
        // and a FrameLayout or NavHostFragment to host this content.
        // If this XML (`activity_main_content.xml`) is the *entire* content for MainActivity:
        setContentView(R.layout.activity_main); // Or your specific layout file name

        setupScheduleCards();
        setupKeywordsSection();
        setupBottomNavigation();

        // If you are using RecyclerView for keywords:
        /*
        recyclerViewKeywords = findViewById(R.id.recyclerViewKeywords);
        recyclerViewKeywords.setLayoutManager(new LinearLayoutManager(this));

        keywordItemList = new ArrayList<>();
        // Add placeholder data or fetch from a data source
        keywordItemList.add(new KeywordItem(R.drawable.ic_avatar_placeholder, "삼육대학교", "시온관(남생활관) 움 확진자 발생에 따른 경..."));
        keywordItemList.add(new KeywordItem(R.drawable.ic_avatar_placeholder, "학사지원팀", "email@fakedomain.net"));
        // Add more items as needed

        keywordAdapter = new KeywordAdapter(this, keywordItemList);
        recyclerViewKeywords.setAdapter(keywordAdapter);
        */

        // If you're using the static <include> for keyword items,
        // you can find their views if you need to interact with them,
        // but they are already populated by the XML.
        // Example for static item 1's avatar (if you gave it a unique ID in the included layout)
        // ImageView avatar1 = findViewById(R.id.imageViewAvatar1); // You'd need to add this ID in the static include
    }

    private void setupBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        // 초기 선택 상태 설정 (홈 화면)
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        // 현재 선택된 아이템의 아이콘을 _active 버전으로 변경
        if (itemId == R.id.navigation_home) {
            item.setIcon(R.drawable.ic_menu_home_active);
        } else if (itemId == R.id.navigation_calendar) {
            item.setIcon(R.drawable.ic_menu_calendar_active);
        } else if (itemId == R.id.navigation_add) {
            item.setIcon(R.drawable.ic_menu_add_active);
        } else if (itemId == R.id.navigation_notification) {
            item.setIcon(R.drawable.ic_menu_notification_active);
        } else if (itemId == R.id.navigation_profile) {
            item.setIcon(R.drawable.ic_menu_person_active);
        }

        // 이전에 선택된 아이템의 아이콘을 원래 버전으로 변경
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = bottomNavigationView.getMenu().getItem(i);
            if (menuItem.getItemId() != itemId) {
                if (menuItem.getItemId() == R.id.navigation_home) {
                    menuItem.setIcon(R.drawable.ic_menu_home);
                } else if (menuItem.getItemId() == R.id.navigation_calendar) {
                    menuItem.setIcon(R.drawable.ic_menu_calendar);
                } else if (menuItem.getItemId() == R.id.navigation_add) {
                    menuItem.setIcon(R.drawable.ic_menu_add);
                } else if (menuItem.getItemId() == R.id.navigation_notification) {
                    menuItem.setIcon(R.drawable.ic_menu_notification);
                } else if (menuItem.getItemId() == R.id.navigation_profile) {
                    menuItem.setIcon(R.drawable.ic_menu_person);
                }
            }
        }

        return true;
    }

    private void setupScheduleCards() {
        // 오늘의 할일 카드 설정
        TextView todayTitle = findViewById(R.id.cardToday).findViewById(R.id.textViewCardTitle);
        TextView todayDate = findViewById(R.id.cardToday).findViewById(R.id.textViewCardDate);
        TextView todayContent = findViewById(R.id.cardToday).findViewById(R.id.textViewCardContent);

        todayTitle.setText("오늘의 할일");
        todayDate.setText(getFormattedDate(0));
        todayContent.setText("추가 전체보기");

        // 내일의 일정 카드 설정
        TextView tomorrowTitle = findViewById(R.id.cardTomorrow).findViewById(R.id.textViewCardTitle);
        TextView tomorrowDate = findViewById(R.id.cardTomorrow).findViewById(R.id.textViewCardDate);
        TextView tomorrowContent = findViewById(R.id.cardTomorrow).findViewById(R.id.textViewCardContent);

        tomorrowTitle.setText("내일의 일정");
        tomorrowDate.setText(getFormattedDate(1));
        tomorrowContent.setText("시간표가 등록되지 않음");

        // 주간 일정 카드 설정
        TextView weekTitle = findViewById(R.id.cardWeek).findViewById(R.id.textViewCardTitle);
        TextView weekDate = findViewById(R.id.cardWeek).findViewById(R.id.textViewCardDate);
        TextView weekContent = findViewById(R.id.cardWeek).findViewById(R.id.textViewCardContent);

        weekTitle.setText("주간 일정");
        weekDate.setText(getWeekDateRange());
        weekContent.setText("이번 주 시험 일정");
    }

    private void setupKeywordsSection() {
        TextView textViewKeywordsSeeAll = findViewById(R.id.textViewKeywordsSeeAll);
        textViewKeywordsSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "전체보기 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFormattedDate(int daysToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("M월 d일 (E)", new Locale("ko", "KR"));
        return dateFormat.format(calendar.getTime());
    }

    private String getWeekDateRange() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("M/d", new Locale("ko", "KR"));
        String startDate = dateFormat.format(calendar.getTime());
        
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        String endDate = dateFormat.format(calendar.getTime());
        
        return startDate + " ~ " + endDate;
    }

    // If using RecyclerView, create a data model class (KeywordItem.java)
    /*
    public static class KeywordItem {
        private int avatarResId; // Use String for URL if loading from network
        private String title;
        private String subtitle;

        public KeywordItem(int avatarResId, String title, String subtitle) {
            this.avatarResId = avatarResId;
            this.title = title;
            this.subtitle = subtitle;
        }

        public int getAvatarResId() {
            return avatarResId;
        }

        public String getTitle() {
            return title;
        }

        public String getSubtitle() {
            return subtitle;
        }
    }
    */

    // If using RecyclerView, create an Adapter (KeywordAdapter.java)
    /*
    // See a typical RecyclerView adapter implementation.
    // You would inflate R.layout.list_item_keyword in its onCreateViewHolder
    // and bind data in onBindViewHolder.
    */
}
