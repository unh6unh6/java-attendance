package model;

import java.util.Collections;
import java.util.Map;

public class AttendanceHistory {

    private final Map<AttendanceDate, AttendanceTime> history;

    public AttendanceHistory(final Map<AttendanceDate, AttendanceTime> history) {
        this.history = history;
    }

    public void add(final AttendanceDate date, final AttendanceTime time) {
        history.put(date, time);
    }

    public Map<AttendanceDate, AttendanceTime> getHistory() {
        return Collections.unmodifiableMap(history);
    }
}
