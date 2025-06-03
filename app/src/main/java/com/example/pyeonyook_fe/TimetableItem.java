package com.example.pyeonyook_fe;

public class TimetableItem {
    private String subjectName;   // 과목명
    private int startTime;        // 시작 시간 (0: 9시, 1: 10시, ..., 9: 6시)
    private int duration;         // 지속 시간 (시간 단위)
    private int color;            // 배경 색상
    private String dayOfWeek;     // 요일 (월, 화, 수, 목, 금)

    // 생성자
    public TimetableItem(String subjectName, int startTime, int duration, int color, String dayOfWeek) {
        this.subjectName = subjectName;
        this.startTime = startTime;
        this.duration = duration;
        this.color = color;
        this.dayOfWeek = dayOfWeek;
    }

    // Getter 메서드들
    public String getSubjectName() { return subjectName; }
    public int getStartTime() { return startTime; }
    public int getDuration() { return duration; }
    public int getColor() { return color; }
    public String getDayOfWeek() { return dayOfWeek; }

    // Setter 메서드들
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public void setStartTime(int startTime) { this.startTime = startTime; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setColor(int color) { this.color = color; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    // 유틸리티 메서드들
    public String getFormattedStartTime() {
        int hour = (startTime + 9) % 12;
        if (hour == 0) hour = 12;
        return String.format("%02d:00", hour);
    }

    public String getFormattedEndTime() {
        int endTime = startTime + duration;
        int hour = (endTime + 9) % 12;
        if (hour == 0) hour = 12;
        return String.format("%02d:00", hour);
    }

    public String getTimeRangeText() {
        return getFormattedStartTime() + " - " + getFormattedEndTime();
    }

    public static int getDayIndex(String dayOfWeek) {
        switch (dayOfWeek) {
            case "월": return 0;
            case "화": return 1;
            case "수": return 2;
            case "목": return 3;
            case "금": return 4;
            default: return -1;
        }
    }

    public static String getDayString(int dayIndex) {
        switch (dayIndex) {
            case 0: return "월";
            case 1: return "화";
            case 2: return "수";
            case 3: return "목";
            case 4: return "금";
            default: return "";
        }
    }
}
