package view;

import dto.AttendanceHistoryDto;

public class ResultView {

    private static final String LINE = System.lineSeparator();
    private static final String ATTENDANCE_MODIFY_RESULT_FORMAT = "%s -> %s 수정 완료!";

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

    private void printAttendanceHistoryDto(final AttendanceHistoryDto dto) {
        System.out.println(dto.toString());
    }
}
