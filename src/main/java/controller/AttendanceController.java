package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import model.AttendanceType;
import model.Campus;
import model.Crew;
import model.Crews;
import util.StringParser;
import util.TimeFormatter;
import view.InputView;
import view.ResultView;

public class AttendanceController {

    private final InputView inputView;
    private final ResultView resultView;
    private final StringParser stringParser;

    private final Campus campus;

    public AttendanceController(final InputView inputView, final ResultView resultView,
                                final StringParser stringParser, final Campus campus) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.stringParser = stringParser;
        this.campus = campus;
    }

    public void start(final Crews crews) {
        String nickname = inputView.readNickname();
        Crew crew = crews.findCrewByNickname(nickname);

        String attendanceTimeInput = inputView.readAttendanceTime();
        LocalTime attendanceTime = stringParser.parseLocalTime(attendanceTimeInput);
        LocalDateTime attendanceDateTime = LocalDateTime.of(getTodayDate(), attendanceTime);

        campus.validateOperationDateTime(attendanceDateTime);

        crew.doAttendance(attendanceDateTime);

        resultView.printAttendanceHistory(
                TimeFormatter.formatDateTime(attendanceDateTime),
                AttendanceType.from(attendanceDateTime)
        );
    }

    private LocalDate getTodayDate() {
        return LocalDate.now();
    }
}
