package com.example.pyeonyook_fe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kizitonwose.calendar.core.CalendarDay;
import com.kizitonwose.calendar.core.CalendarMonth;
import com.kizitonwose.calendar.core.DayPosition;
import com.kizitonwose.calendar.view.CalendarView;
import com.kizitonwose.calendar.view.MonthDayBinder;
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder;
import com.kizitonwose.calendar.view.ViewContainer;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;
import kotlin.Unit;

public class Calendar extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "CalendarActivity";

    private BottomNavigationView bottomNavigationView;
    private CalendarView calendarView;
    private EditText searchEditText;
    private RecyclerView scheduleList;
    private TextView monthText;
    private TextView monthYearText;
    private TextView eventDateText;
    private YearMonth currentMonth;
    private YearMonth selectedMonth;
    private LocalDate selectedDate;
    private LocalDate previousSelectedDate;
    private boolean isScrolling = false;
    private CalendarMonth lastVisibleMonth = null;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateMonthRunnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate 시작");

            setContentView(R.layout.activity_calendar);
            Log.d(TAG, "setContentView 완료");

            // 뷰 초기화
            try {
                initializeViews();
                Log.d(TAG, "뷰 초기화 완료");
            } catch (Exception e) {
                Log.e(TAG, "뷰 초기화 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
                Toast.makeText(this, "화면 초기화 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // 바텀 네비게이션 설정
            try {
                setupBottomNavigation();
                Log.d(TAG, "바텀 네비게이션 설정 완료");
            } catch (Exception e) {
                Log.e(TAG, "바텀 네비게이션 설정 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
            }

            // 캘린더 설정
            try {
                setupCalendar();
                Log.d(TAG, "캘린더 설정 완료");
            } catch (Exception e) {
                Log.e(TAG, "캘린더 설정 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
            }

            Log.d(TAG, "onCreate 완료");
        } catch (Exception e) {
            Log.e(TAG, "onCreate에서 예상치 못한 오류 발생: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "앱 실행 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initializeViews() {
        try {
            calendarView = findViewById(R.id.calendarView);
            if (calendarView == null) {
                Log.e(TAG, "calendarView를 찾을 수 없습니다.");
                throw new RuntimeException("calendarView를 찾을 수 없습니다.");
            }

            searchEditText = findViewById(R.id.searchEditText);
            if (searchEditText == null) {
                Log.e(TAG, "searchEditText를 찾을 수 없습니다.");
                throw new RuntimeException("searchEditText를 찾을 수 없습니다.");
            }

            monthText = findViewById(R.id.monthYearText);
            if (monthText == null) {
                Log.e(TAG, "monthYearText를 찾을 수 없습니다.");
                throw new RuntimeException("monthYearText를 찾을 수 없습니다.");
            }

            monthYearText = findViewById(R.id.monthYearText);
            if (monthYearText == null) {
                Log.e(TAG, "monthYearText를 찾을 수 없습니다.");
                throw new RuntimeException("monthYearText를 찾을 수 없습니다.");
            }

            eventDateText = findViewById(R.id.eventDateText);
            if (eventDateText == null) {
                Log.e(TAG, "eventDateText를 찾을 수 없습니다.");
                throw new RuntimeException("eventDateText를 찾을 수 없습니다.");
            }

            scheduleList = findViewById(R.id.scheduleList);
            if (scheduleList == null) {
                Log.e(TAG, "scheduleList를 찾을 수 없습니다.");
                throw new RuntimeException("scheduleList를 찾을 수 없습니다.");
            }

            bottomNavigationView = findViewById(R.id.bottomNavigation);
            if (bottomNavigationView == null) {
                Log.e(TAG, "bottomNavigationView를 찾을 수 없습니다.");
                throw new RuntimeException("bottomNavigationView를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            Log.e(TAG, "initializeViews에서 오류 발생: " + e.getMessage());
            throw e;
        }
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.navigation_calendar);
    }

    private void setupCalendar() {
        currentMonth = YearMonth.now();
        selectedMonth = currentMonth;
        selectedDate = LocalDate.now(); // 기본값을 오늘 날짜로 설정

        // 현재 날짜와 월 표시
        LocalDate today = LocalDate.now();
        monthText.setText(String.valueOf(today.getDayOfMonth()));
        monthYearText.setText(String.valueOf(today.getMonthValue()));
        
        // 오늘 날짜로 eventDateText 초기화
        updateEventDateText(selectedDate);

        // 캘린더 설정
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        calendarView.setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), weekFields.getFirstDayOfWeek());
        calendarView.scrollToMonth(currentMonth);

        // 월 헤더 바인더 설정
        calendarView.setMonthHeaderBinder(new MonthHeaderFooterBinder<MonthViewContainer>() {
            @Override
            public MonthViewContainer create(View view) {
                return new MonthViewContainer(view);
            }

            @Override
            public void bind(MonthViewContainer container, CalendarMonth calendarMonth) {
                // 스크롤과 함께 월이 변경될 때마다 즉시 업데이트
                selectedMonth = calendarMonth.getYearMonth();
                monthYearText.setText(String.valueOf(selectedMonth.getMonthValue()));
                Log.d(TAG, "월이 변경됨: " + selectedMonth.getMonthValue());
            }
        });

        // 날짜 셀 바인더 설정
        calendarView.setDayBinder(new MonthDayBinder<DayViewContainer>() {
            @Override
            public DayViewContainer create(View view) {
                return new DayViewContainer(view);
            }

            @Override
            public void bind(DayViewContainer container, CalendarDay day) {
                container.textView.setText(String.valueOf(day.getDate().getDayOfMonth()));
                container.day = day;

                // 선택된 날짜 표시
                if (day.getDate().equals(selectedDate)) {
                    container.todayIndicator.setVisibility(View.VISIBLE);
                } else {
                    container.todayIndicator.setVisibility(View.GONE);
                }

                // 공휴일이거나 일요일인 경우 빨간색으로 표시
                if (day.getPosition() == DayPosition.MonthDate) {
                    if (KoreanHoliday.isHoliday(day.getDate()) || day.getDate().getDayOfWeek().getValue() == 7) {
                        container.textView.setTextColor(0xFFFF0000); // 빨간색
                    } else if (day.getDate().getDayOfWeek().getValue() == 6) {
                        container.textView.setTextColor(0xFF0000FF); // 파란색 (토요일)
                    } else {
                        container.textView.setTextColor(0xFF000000); // 검정색
                    }
                }
            }
        });

        // 스크롤 관련 코드 제거 (더 이상 필요하지 않음)
        calendarView.setOnScrollListener(null);
        calendarView.setMonthScrollListener(null);
    }

    private void updateEventDateText(LocalDate date) {
        String[] weekDays = {"월", "화", "수", "목", "금", "토", "일"};
        String weekDay = weekDays[date.getDayOfWeek().getValue() - 1];
        eventDateText.setText(String.format("%d월 %d일 (%s)", date.getMonthValue(), date.getDayOfMonth(), weekDay));
    }

    private class DayViewContainer extends ViewContainer {
        TextView textView;
        View todayIndicator;
        CalendarDay day;

        DayViewContainer(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.calendarDayText);
            todayIndicator = view.findViewById(R.id.todayIndicator);
            view.setOnClickListener(v -> {
                if (day.getPosition() == DayPosition.MonthDate) {
                    previousSelectedDate = selectedDate;
                    selectedDate = day.getDate();
                    updateEventDateText(selectedDate);
                    
                    if (previousSelectedDate != null) {
                        calendarView.notifyDateChanged(previousSelectedDate);
                    }
                    calendarView.notifyDateChanged(selectedDate);
                    
                    // TODO: 선택된 날짜의 일정 표시
                }
            });
        }
    }

    private class MonthViewContainer extends ViewContainer {
        TextView sundayText;
        TextView mondayText;
        TextView tuesdayText;
        TextView wednesdayText;
        TextView thursdayText;
        TextView fridayText;
        TextView saturdayText;

        MonthViewContainer(@NonNull View view) {
            super(view);
            sundayText = view.findViewById(R.id.sundayText);
            mondayText = view.findViewById(R.id.mondayText);
            tuesdayText = view.findViewById(R.id.tuesdayText);
            wednesdayText = view.findViewById(R.id.wednesdayText);
            thursdayText = view.findViewById(R.id.thursdayText);
            fridayText = view.findViewById(R.id.fridayText);
            saturdayText = view.findViewById(R.id.saturdayText);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();


        if (itemId == R.id.navigation_home) {
            item.setIcon(R.drawable.ic_menu_home_active);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        } else if (itemId == R.id.navigation_calendar) {
            item.setIcon(R.drawable.ic_menu_calendar_active);
        } else if (itemId == R.id.navigation_add) {
            item.setIcon(R.drawable.ic_menu_add_active);

        } else if (itemId == R.id.navigation_notification) {
            item.setIcon(R.drawable.ic_menu_notification_active);

        } else if (itemId == R.id.navigation_profile) {
            item.setIcon(R.drawable.ic_menu_person_active);
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
            finish();
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
}