package util;

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
}
