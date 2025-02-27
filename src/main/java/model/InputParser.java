package model;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class InputParser {

    public LocalTime parseTimeInput(final String input) {
        try {
            return LocalTime.parse(input, AttendanceDateTimeFormatter.timeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 시간 입력 형식입니다.");
        }
    }
}
