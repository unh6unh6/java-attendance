package model;

import java.util.Map;

public class AttendanceBook {

    private final Map<String, AttendanceHistory> attendanceBook;

    public AttendanceBook(final Map<String, AttendanceHistory> attendanceBook) {
        this.attendanceBook = attendanceBook;
    }

    public AttendanceHistory getAttendanceHistoryByNickname(final String nickname) {
        return attendanceBook.get(nickname);
    }
}
