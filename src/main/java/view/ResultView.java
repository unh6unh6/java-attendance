package view;

import dto.AttendanceHistoryDto;

public class ResultView {

    private static final String LINE = System.lineSeparator();

    public void printAttendanceCheckResult(final AttendanceHistoryDto dto) {
        System.out.println(LINE);
        printAttendanceHistoryDto(dto);
    }

    private void printAttendanceHistoryDto(final AttendanceHistoryDto dto) {
        System.out.println(dto.toString());
    }
}
