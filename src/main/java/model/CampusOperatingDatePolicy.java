package model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CampusOperatingDatePolicy {

    public static boolean isOperatingDate(final LocalDate date) {
        return !isWeekend(date.getDayOfWeek()) && !Holiday.isHoliday(date);
    }

    private static boolean isWeekend(final DayOfWeek dayOfWeek) {
        return dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY);
    }
}
