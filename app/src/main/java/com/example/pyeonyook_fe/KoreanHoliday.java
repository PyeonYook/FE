package com.example.pyeonyook_fe;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class KoreanHoliday {
    private static final Set<LocalDate> holidays = new HashSet<>();

    static {
        // 양력 공휴일
        addHoliday(2024, 1, 1);  // 신정
        addHoliday(2024, 3, 1);  // 삼일절
        addHoliday(2024, 5, 5);  // 어린이날
        addHoliday(2024, 6, 6);  // 현충일
        addHoliday(2024, 8, 15); // 광복절
        addHoliday(2024, 10, 3); // 개천절
        addHoliday(2024, 10, 9); // 한글날
        addHoliday(2024, 12, 25); // 크리스마스

        // 음력 공휴일 (2024년 기준)
        addHoliday(2024, 2, 9);  // 설날
        addHoliday(2024, 2, 10); // 설날
        addHoliday(2024, 2, 11); // 설날
        addHoliday(2024, 4, 10); // 부처님오신날
        addHoliday(2024, 9, 16); // 추석
        addHoliday(2024, 9, 17); // 추석
        addHoliday(2024, 9, 18); // 추석

        // 대체공휴일
        addHoliday(2024, 2, 12); // 설날 대체공휴일
        addHoliday(2024, 5, 6);  // 어린이날 대체공휴일
        addHoliday(2024, 9, 19); // 추석 대체공휴일
    }

    private static void addHoliday(int year, int month, int day) {
        holidays.add(LocalDate.of(year, month, day));
    }

    public static boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
} 