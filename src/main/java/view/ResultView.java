package view;

import dto.AttendanceHistoryDto;
import java.util.List;
import model.AttendanceTypeCountResult;
import model.DismissalType;

public class ResultView {

    private static final String LINE = System.lineSeparator();
    private static final String ATTENDANCE_MODIFY_RESULT_FORMAT = "%s -> %s 수정 완료!";
    private static final String ATTENDANCE_HISTORY_RESULT_FORMAT = "이번 달 %s의 출석 기록입니다.";
    private static final String ATTENDANCE_TYPE_COUNT_RESULT_FORMAT = "%s: %d회";
    private static final String DISMISSAL_TYPE_FORMAT = "%s 대상자입니다.";

    public void printAttendanceCheckResult(final AttendanceHistoryDto dto) {
        System.out.println(LINE);
        printAttendanceHistoryDto(dto);
    }

    public void printAttendanceModifyResult(
            final AttendanceHistoryDto oldDto,
            final AttendanceHistoryDto newDto
    ) {
        System.out.printf(LINE + ATTENDANCE_MODIFY_RESULT_FORMAT,
                oldDto.toString(), newDto.toStringWithoutDate());
    }

    public void printAttendanceHistory(final String nickname, final List<AttendanceHistoryDto> dtos) {
        System.out.printf(ATTENDANCE_HISTORY_RESULT_FORMAT + LINE, nickname);
        dtos.forEach(this::printAttendanceHistoryDto);
        System.out.println(LINE);
    }

    public void printAttendanceTypeCountResult(final AttendanceTypeCountResult attendanceTypeCountResult) {
        attendanceTypeCountResult.typeResult().entrySet().forEach(
                entry -> System.out.printf(ATTENDANCE_TYPE_COUNT_RESULT_FORMAT, entry.getKey(), entry.getValue())
        );
        System.out.println(LINE);
    }

    private void printAttendanceHistoryDto(final AttendanceHistoryDto dto) {
        System.out.println(dto.toString());
    }

    public void printDismissalType(final DismissalType dismissalType) {
        System.out.printf(DISMISSAL_TYPE_FORMAT, dismissalType.getName());
    }
}
