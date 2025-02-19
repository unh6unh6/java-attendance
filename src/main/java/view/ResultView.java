package view;

import static model.AttendanceType.DEFAULT_TIME;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import model.AttendanceType;
import model.SubjectType;
import util.TimeFormatter;

public class ResultView {

    public static final String LINE = System.lineSeparator();
    private static final String ATTENDANCE_HISTORY_FORM = "%s (%s)";
    private static final String MODIFY_HISTORY_FORM = "%s (%s) -> %s (%s) 수정 완료!";
    private static final String ATTENDANCE_HISTORY_BY_CREW_FORM = "이번 달 %s의 출석 기록입니다.";
    private static final String DEFAULT_TIME_FORM = "--:--";
    private static final String ATTENDANCE_TYPE_COUNT_FORM = """
            출석: %d회
            지각: %d회
            결석: %d회
            """;
    private static final String SUBJECT_TYPE_FORM = "%s 대상자입니다.";

    public void printAttendanceHistory(final String attendanceTime, final AttendanceType attendanceType) {
        System.out.printf(ATTENDANCE_HISTORY_FORM + LINE, attendanceTime, attendanceType.name());
    }

    public void printModifyHistory(final String previousTime, final AttendanceType previousType,
                                   final String modifyTime, final AttendanceType modifyType) {
        System.out.printf(LINE + MODIFY_HISTORY_FORM + LINE, previousTime, previousType.name(),
                modifyTime, modifyType.name());
    }

    public void printAttendanceHistoryResultByCrew(
            final String nickname,
            final List<LocalDateTime> attendanceHistory,
            final Map<AttendanceType, Integer> result,
            final SubjectType subjectType
    ) {
        System.out.printf(LINE + ATTENDANCE_HISTORY_BY_CREW_FORM + LINE + LINE, nickname);
        printAttendanceHistories(attendanceHistory);
        printAttendanceTypeCount(result);
        printSubjectType(subjectType);
    }

    private void printAttendanceHistories(final List<LocalDateTime> attendanceHistory) {
        attendanceHistory.forEach(localDateTime -> printAttendanceHistory(
                getAttendanceTimeFormat(localDateTime),
                AttendanceType.from(localDateTime)));
    }

    private String getAttendanceTimeFormat(final LocalDateTime localDateTime) {
        if (DEFAULT_TIME.equals(localDateTime)) {
            return DEFAULT_TIME_FORM;
        }
        return TimeFormatter.formatDateTime(localDateTime);
    }

    private void printAttendanceTypeCount(final Map<AttendanceType, Integer> result) {
        System.out.printf(LINE + ATTENDANCE_TYPE_COUNT_FORM + LINE,
                result.get(AttendanceType.출석),
                result.get(AttendanceType.지각),
                result.get(AttendanceType.결석)
        );
    }

    private void printSubjectType(final SubjectType subjectType) {
        if (subjectType.equals(SubjectType.해당없음)) {
            return;
        }
        System.out.printf(SUBJECT_TYPE_FORM + LINE, subjectType.name());
    }
}
