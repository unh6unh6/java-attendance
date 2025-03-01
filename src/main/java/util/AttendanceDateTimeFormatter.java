package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AttendanceDateTimeFormatter {

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일 E요일");
    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public static String formatLocalDate(final LocalDate localDate) {
        return localDate.format(dateFormatter);
    }

    public static String formatLocalTime(final LocalTime localTime) {
        return localTime.format(timeFormatter);
    }

}
