package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AttendanceHistory {

    private final Map<LocalDate, AttendanceDate> attendanceDateTable;
    private final Map<AttendanceDate, AttendanceTime> history;

    public AttendanceHistory() {
        attendanceDateTable = new HashMap<>();
        history = new HashMap<>();
    }

    public void add(final AttendanceDate date, final AttendanceTime time) {
        if (history.containsKey(date)) {
            throw new IllegalArgumentException("[ERROR] 이미 출석 기록이 있습니다. 수정 기능을 이용해주세요.");
        }
        attendanceDateTable.put(date.getDate(), date);
        history.put(date, time);
    }

    public void modify(final AttendanceDate date, final AttendanceTime time) {
        attendanceDateTable.putIfAbsent(date.getDate(), date);
        history.put(date, time);
    }

    public Map<AttendanceDate, AttendanceTime> getHistory() {
        return Collections.unmodifiableMap(history);
    }
}
