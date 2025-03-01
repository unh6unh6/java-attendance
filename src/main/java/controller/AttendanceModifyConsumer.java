package controller;

import java.time.LocalDate;
import java.util.function.BiConsumer;
import model.AttendanceBook;
import model.AttendanceHistory;
import view.InputView;
import view.ResultView;

public class AttendanceModifyConsumer implements BiConsumer<AttendanceBook, LocalDate> {

    private final InputView inputView;
    private final ResultView resultView;

    public AttendanceModifyConsumer(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    @Override
    public void accept(final AttendanceBook attendanceBook, final LocalDate localDate) {
        AttendanceHistory attendanceHistory = inputNicknameAndGetAttendanceHistory(attendanceBook);

    }

    private AttendanceHistory inputNicknameAndGetAttendanceHistory(final AttendanceBook attendanceBook) {
        String nickname = inputView.getAttendanceModifyCrewNickname();
        return attendanceBook.getAttendanceHistoryByNickname(nickname);
    }
}
