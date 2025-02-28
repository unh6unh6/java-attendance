package model;

import java.util.Collections;
import java.util.Map;

public class AttendanceHistory {

    private final Map<AttendanceDate, AttendanceTime> history;

    public AttendanceHistory(final Map<AttendanceDate, AttendanceTime> history) {
        this.history = history;
    }

    public void add(final AttendanceDate date, final AttendanceTime time) {
        if (history.containsKey(date)) {
            throw new IllegalArgumentException("[ERROR] 이미 출석 기록이 있습니다. 수정 기능을 이용해주세요.");
        }
        history.put(date, time);
    }

    public void modify(final AttendanceDate date, final AttendanceTime time) {
        history.put(date, time);
    }

    public Map<AttendanceDate, AttendanceTime> getHistory() {
        return Collections.unmodifiableMap(history);
    }
}
