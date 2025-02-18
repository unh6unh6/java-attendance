package controller;

import java.time.LocalTime;
import util.StringParser;
import view.InputView;

public class AttendanceController {

    private final InputView inputView;
    private final StringParser stringParser;

    public AttendanceController(final InputView inputView, final StringParser stringParser) {
        this.inputView = inputView;
        this.stringParser = stringParser;
    }

    public void start() {
        String nickname = inputView.readNickname();
        String attendanceTimeInput = inputView.readAttendanceTime();
        LocalTime attendanceTime = stringParser.parseLocalTime(attendanceTimeInput);
    }
}
