package model;

import java.time.Duration;
import java.time.LocalTime;

public class AttendanceTime {
    private final LocalTime time;

    public AttendanceTime(final LocalTime time) {
        validateCampusOperatingTime(time);
        this.time = time;
    }

    public Duration getOverDuration(final LocalTime compareTime) {
        return Duration.between(compareTime, time);
    }

    private void validateCampusOperatingTime(final LocalTime time) {
        if (!CampusOperatingPolicy.isOperatingTime(time)) {
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간이 아닙니다.");
        }
    }
}
