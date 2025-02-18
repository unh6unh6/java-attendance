package controller;

import java.time.LocalTime;
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

        // TODO : null 대신 AttendanceType 계산한 enum 넣기
        resultView.printAttendanceHistory(attendanceTimeInput, null);
    }
}
