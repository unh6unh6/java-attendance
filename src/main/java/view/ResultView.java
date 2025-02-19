package view;

import model.AttendanceType;

public class ResultView {

    public static final String LINE = System.lineSeparator();
    private static final String ATTENDANCE_HISTORY_FORM = "%s (%s)";
    private static final String MODIFY_HISTORY_FORM = "%s (%s) -> %s (%s) 수정 완료!";

    public void printAttendanceHistory(final String attendanceTime, final AttendanceType attendanceType) {
        System.out.printf(ATTENDANCE_HISTORY_FORM + LINE, attendanceTime, attendanceType.name());
    }

    public void printModifyHistory(final String previousTime, final AttendanceType previousType,
                                   final String modifyTime, final AttendanceType modifyType) {
        System.out.printf(LINE + MODIFY_HISTORY_FORM + LINE, previousTime, previousType.name(),
                modifyTime, modifyType.name());
    }
}
