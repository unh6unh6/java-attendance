package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.AttendanceType;
import util.StringParser;
import view.InputView;
import view.ResultView;

public class AttendanceController {

    private final InputView inputView;
    private final ResultView resultView;
    private final StringParser stringParser;

    public AttendanceController(final InputView inputView, final ResultView resultView,
                                final StringParser stringParser) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.stringParser = stringParser;
    }

    public void start() {
        String nickname = inputView.readNickname();
        String attendanceTimeInput = inputView.readAttendanceTime();
        LocalTime attendanceTime = stringParser.parseLocalTime(attendanceTimeInput);
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), attendanceTime);
        resultView.printAttendanceHistory(attendanceTimeInput, AttendanceType.from(localDateTime));
    }
}
