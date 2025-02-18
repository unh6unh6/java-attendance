package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import util.TimeFormatter;

public class Campus {

    private static final int CHRISTMAS_DAY = 25;

    private final TimeFormatter timeFormatter;

    public Campus(final TimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
    }

    // 일자 - 주말 및 공휴일 제외
    public void validateOperationTime(final LocalDateTime time) {
        // 일자
        DayOfWeek dayOfWeek = time.getDayOfWeek();
        if (isWeekend(dayOfWeek) || isHoliday(LocalDate.from(time))) {
            throw new IllegalArgumentException("[ERROR] " + timeFormatter.formatDate(time) + "은 등교일이 아닙니다.");
        }
        // 시간

    }

    private boolean isWeekend(final DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(final LocalDate date) {
        return date.getDayOfMonth() == CHRISTMAS_DAY;
    }


}
