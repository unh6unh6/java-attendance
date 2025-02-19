package model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Crew {

    private final String nickname;

    private final Map<Integer, LocalDateTime> attendance = new HashMap<>();

    public Crew(final String nickname) {
        this.nickname = nickname;
    }

    public Map<Integer, LocalDateTime> getAttendance() {
        return Collections.unmodifiableMap(attendance);
    }

    public void doAttendance(final LocalDateTime attendanceTime) {
        int day = attendanceTime.getDayOfMonth();
        if (attendance.containsKey(day)) {
            throw new IllegalArgumentException("[ERROR] 이미 출석했습니다. 수정 기능을 이용해주세요.");
        }
        attendance.put(day, attendanceTime);
    }

    public void doModify(final LocalDateTime modifyDateTime) {
        attendance.put(modifyDateTime.getDayOfMonth(), modifyDateTime);
    }
}
