package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.BiConsumer;
import model.AttendanceBook;
import model.AttendanceHistory;
import util.InputParser;
import view.InputView;

public class AttendanceCheckConsumer implements BiConsumer<AttendanceBook, LocalDate> {

    private final InputView inputView;

    public AttendanceCheckConsumer(final InputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public void accept(final AttendanceBook attendanceBook, final LocalDate date) {
        String nickname = inputView.getAttendanceCheckCrewNickname();
        AttendanceHistory attendanceHistory = attendanceBook.getAttendanceHistoryByNickname(nickname);
        LocalTime time = InputParser.parseTime(inputView.getAttendanceCheckTime());
    }
}
