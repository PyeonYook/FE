package com.example.pyeonyook_fe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;



public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    // 시간표 관련 변수들
    private GridLayout timetableGrid;
    private TextView emptyMessage;
    private TextView dateText;
    private List<TimetableItem> timetableItems;
    private int currentDayOfWeek = 0; // 0: 월요일, 1: 화요일, ...
    private TextView[] dayButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // 레이아웃을 먼저 설정

        setupTimetableUI();  // 그 다음 UI 설정
        setupScheduleCards();
        setupKeywordsSection();
        setupBottomNavigation();
    }

    private void setupBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);  // 리스너 설정 추가
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
            Intent intent = new Intent(MainActivity.this, com.example.pyeonyook_fe.Calendar.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else if (itemId == R.id.navigation_add) {
            item.setIcon(R.drawable.ic_menu_add_active);
            Intent intent = new Intent(MainActivity.this, SYU_more.class);
            startActivity(intent);
        } else if (itemId == R.id.navigation_notification) {
            item.setIcon(R.drawable.ic_menu_notification_active);
            Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.navigation_profile) {
            item.setIcon(R.drawable.ic_menu_person_active);
            Intent intent = new Intent(MainActivity.this, Profile.class);
            startActivity(intent);
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
                Intent intent = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent);
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

    private void setupTimetableUI() {
        // UI 요소 초기화
        timetableGrid = findViewById(R.id.timetableGrid);
        emptyMessage = findViewById(R.id.emptyMessage);
        dateText = findViewById(R.id.dateText);
        timetableItems = new ArrayList<>();

        // 요일 버튼들 초기화
        dayButtons = new TextView[]{
                findViewById(R.id.btnMonday),
                findViewById(R.id.btnTuesday),
                findViewById(R.id.btnWednesday),
                findViewById(R.id.btnThursday),
                findViewById(R.id.btnFriday)
        };

        // 시간표 초기화 및 데이터 로드
        initTimetableGrid();
        setupDayButtons();
        updateDateDisplay();
        loadTimetableData();
    }

    private void initTimetableGrid() {
        if (timetableGrid == null) return;

        timetableGrid.setColumnCount(1);
        timetableGrid.setRowCount(10);

        // 시간별 구분선 추가
        for (int i = 0; i < 10; i++) {
            View line = new View(this);
            line.setBackgroundColor(Color.parseColor("#E0E0E0"));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = GridLayout.LayoutParams.MATCH_PARENT;
            params.height = 1;
            params.rowSpec = GridLayout.spec(i);
            params.columnSpec = GridLayout.spec(0);
            params.setMargins(0, 0, 0, 0);

            line.setLayoutParams(params);
            timetableGrid.addView(line);
        }
    }

    private void setupDayButtons() {
        for (int i = 0; i < dayButtons.length; i++) {
            final int dayIndex = i;
            dayButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectDay(dayIndex);
                    updateDayButtonStyles(dayIndex);
                }
            });
        }

        // 초기 선택 (월요일)
        selectDay(0);
        updateDayButtonStyles(0);
    }

    private void selectDay(int dayOfWeek) {
        currentDayOfWeek = dayOfWeek;
        updateDateDisplay();
        loadTimetableData();
    }

    private void updateDayButtonStyles(int selectedIndex) {
        for (int i = 0; i < dayButtons.length; i++) {
            if (i == selectedIndex) {
                dayButtons[i].setTextColor(Color.parseColor("#1976D2"));
                dayButtons[i].setBackgroundColor(Color.parseColor("#E3F2FD"));
            } else {
                dayButtons[i].setTextColor(Color.parseColor("#666666"));
                dayButtons[i].setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    private void updateDateDisplay() {
        if (dateText == null) return;

        Calendar calendar = Calendar.getInstance();
        // 현재 주의 월요일부터 금요일까지 계산
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.DAY_OF_YEAR, currentDayOfWeek);

        SimpleDateFormat dateFormat = new SimpleDateFormat("M월 d일 (E)", new Locale("ko", "KR"));
        dateText.setText(dateFormat.format(calendar.getTime()));
    }

    private void loadTimetableData() {
        // 현재 요일에 해당하는 시간표 데이터 로드
        timetableItems.clear();

        // 예시 데이터 (실제로는 데이터베이스나 API에서 로드)
        if (currentDayOfWeek == 1) { // 화요일 예시
            timetableItems.add(new TimetableItem("데이터베이스", 1, 2, Color.parseColor("#4CAF50"), "화"));
            timetableItems.add(new TimetableItem("안드로이드 프로그래밍", 4, 2, Color.parseColor("#2196F3"), "화"));
        } else if (currentDayOfWeek == 2) { // 수요일 예시
            timetableItems.add(new TimetableItem("웹 프로그래밍", 2, 1, Color.parseColor("#FF9800"), "수"));
            timetableItems.add(new TimetableItem("네트워크", 5, 2, Color.parseColor("#9C27B0"), "수"));
        }

        displayTimetable();
    }

    private void displayTimetable() {
        // 기존 시간표 아이템들 제거 (구분선은 유지)
        removeExistingTimetableItems();

        if (timetableItems.isEmpty()) {
            showEmptyMessage();
        } else {
            hideEmptyMessage();
            addTimetableItemsToGrid();
        }
    }

    private void removeExistingTimetableItems() {
        if (timetableGrid == null) return;

        for (int i = timetableGrid.getChildCount() - 1; i >= 0; i--) {
            View child = timetableGrid.getChildAt(i);
            if (child instanceof TextView && child.getId() != View.NO_ID) {
                timetableGrid.removeView(child);
            }
        }
    }

    private void addTimetableItemsToGrid() {
        for (TimetableItem item : timetableItems) {
            TextView itemView = new TextView(this);
            itemView.setText(item.getSubjectName());
            itemView.setBackgroundColor(item.getColor());
            itemView.setTextColor(Color.WHITE);
            itemView.setGravity(Gravity.CENTER);
            itemView.setPadding(8, 4, 8, 4);
            itemView.setTextSize(12);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(item.getStartTime(), item.getDuration());
            params.columnSpec = GridLayout.spec(0);
            params.width = GridLayout.LayoutParams.MATCH_PARENT;
            params.height = 0;
            params.setMargins(0, 2, 8, 2);

            itemView.setLayoutParams(params);
            timetableGrid.addView(itemView);
        }
    }

    private void showEmptyMessage() {
        if (emptyMessage != null) {
            emptyMessage.setVisibility(View.VISIBLE);
        }
    }

    private void hideEmptyMessage() {
        if (emptyMessage != null) {
            emptyMessage.setVisibility(View.GONE);
        }
    }


}
