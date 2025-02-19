package model;

import java.time.LocalDate;
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

    public LocalDateTime doModify(final LocalDateTime modifyDateTime, final LocalDate todayDate) {
        LocalDate modifyDate = LocalDate.from(modifyDateTime);
        if (modifyDate.isEqual(todayDate) || modifyDate.isAfter(todayDate)) {
            throw new IllegalArgumentException("[ERROR] 수정 일자는 어제 기록까지만 수정할 수 있습니다.");
        }
        int modifyDay = modifyDateTime.getDayOfMonth();
        LocalDateTime previousTime = attendance.get(modifyDay);
        attendance.put(modifyDay, modifyDateTime);
        return previousTime;
    }
}
