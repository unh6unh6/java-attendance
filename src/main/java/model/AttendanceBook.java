package model;

import java.util.Map;

public class AttendanceBook {

    private final Map<String, AttendanceHistory> attendanceBook;

    public AttendanceBook(final Map<String, AttendanceHistory> attendanceBook) {
        this.attendanceBook = attendanceBook;
    }

    public AttendanceHistory getAttendanceHistoryByNickname(final String nickname) {
        if (!attendanceBook.containsKey(nickname)) {
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        return attendanceBook.get(nickname);
    }
}
