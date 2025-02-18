package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {
    // 12월 05일 화요일 09:59
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM월 dd일 E요일 HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM월 dd일 E요일");

    public String formatDateTime(final LocalDateTime localDateTime) {
        return localDateTime.format(dateTimeFormatter);
    }

    public String formatDate(final LocalDateTime localDateTime) {
        return localDateTime.format(dateFormatter);
    }

}
