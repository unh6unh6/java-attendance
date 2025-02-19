package util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class StringParser {

    public LocalTime parseLocalTime(final String input) {
        try {
            return LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 시간이 HH:MM 형식에 맞지 않습니다.");
        }
    }

    public int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 정수 문자열이여야합니다.");
        }
    }

    public LocalDate parseLocalDate(final String input) {
        int day = parseInt(input);
        try {
            return LocalDate.of(2024, 12, day);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 유효한 일자이여야합니다.");
        }
    }
}
