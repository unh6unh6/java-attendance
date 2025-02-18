package view;

import model.AttendanceType;

public class ResultView {

    public static final String LINE = System.lineSeparator();
    private static final String ATTENDANCE_HISTORY_FORM = "%s (%s)";

    public void printAttendanceHistory(final String attendanceTime, final AttendanceType attendanceType) {
        System.out.printf(ATTENDANCE_HISTORY_FORM + LINE, attendanceTime, attendanceType.name());
    }
}
