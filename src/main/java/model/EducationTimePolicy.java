package model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public enum EducationTimePolicy {
    MON(DayOfWeek.MONDAY, LocalTime.of(13, 0), LocalTime.of(18, 0)),
    TUE(DayOfWeek.TUESDAY, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    WED(DayOfWeek.WEDNESDAY, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    THU(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    FRI(DayOfWeek.FRIDAY, LocalTime.of(10, 0), LocalTime.of(18, 0)),
    SAT(DayOfWeek.SATURDAY, LocalTime.MIN, LocalTime.MIN),
    SUN(DayOfWeek.SUNDAY, LocalTime.MIN, LocalTime.MIN);

    private final DayOfWeek dayOfWeek;
    private final LocalTime openTime;
    private final LocalTime closeTime;

    EducationTimePolicy(final DayOfWeek dayOfWeek, final LocalTime openTime, final LocalTime closeTime) {
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

}
