package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {
    // 12월 05일 화요일 09:59
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM월 dd일 E요일 HH:mm");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일 E요일");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static String formatDateTime(final LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static String formatDate(final LocalDate localDate) {
        return localDate.format(dateFormatter);
    }

    public static String formatTime(final LocalTime localTime) {
        return localTime.format(timeFormatter);
    }
}
