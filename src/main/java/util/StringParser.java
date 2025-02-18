package util;

import java.time.LocalTime;

public class StringParser {

    public LocalTime parseLocalTime(String input) {
        return LocalTime.parse(input);
    }
}
