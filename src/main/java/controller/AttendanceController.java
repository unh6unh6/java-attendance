package controller;

import dto.DismissalCrewDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import model.AttendanceType;
import model.Campus;
import model.Crew;
import model.Crews;
import model.SubjectType;
import util.StringParser;
import util.TimeFormatter;
import view.InputView;
import view.ResultView;

public class AttendanceController {

    private final InputView inputView;
    private final ResultView resultView;
    private final Campus campus;

    public AttendanceController(final InputView inputView, final ResultView resultView, final Campus campus) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.campus = campus;
    }

    public void start(final Crews crews) {
        checkAttendance(crews);
        modifyAttendance(crews);
        checkAttendanceHistoryByCrew(crews);
        checkDismissalCrews(crews);
    }

    private void checkAttendance(final Crews crews) {
        String nickname = inputView.readNickname();
        Crew crew = crews.findCrewByNickname(nickname);

        String attendanceTimeInput = inputView.readAttendanceTime();
        LocalTime attendanceTime = StringParser.parseLocalTime(attendanceTimeInput);
        LocalDateTime attendanceDateTime = LocalDateTime.of(getTodayDate(), attendanceTime);

        campus.validateOperationDate(LocalDate.from(attendanceDateTime));
        campus.validateOperationTime(attendanceDateTime);

        crew.doAttendance(attendanceDateTime);

        resultView.printAttendanceHistory(
                TimeFormatter.formatDateTime(attendanceDateTime),
                AttendanceType.from(attendanceDateTime)
        );
    }

    private void modifyAttendance(final Crews crews) {
        String nickname = inputView.readModifyNickname();
        Crew crew = crews.findCrewByNickname(nickname);

        String dayInput = inputView.readModifyDay();
        LocalDate modifyDate = StringParser.parseLocalDate(dayInput);
        campus.validateOperationDate(modifyDate);

        String timeInput = inputView.readModifyTime();
        LocalTime modifyTime = StringParser.parseLocalTime(timeInput);
        LocalDateTime modifyDateTime = LocalDateTime.of(modifyDate, modifyTime);
        campus.validateOperationTime(modifyDateTime);

        LocalDateTime previousTime = crew.doModify(modifyDateTime, getTodayDate());
        resultView.printModifyHistory(
                TimeFormatter.formatDateTime(previousTime),
                AttendanceType.from(previousTime),
                TimeFormatter.formatTime(modifyTime),
                AttendanceType.from(modifyDateTime)
        );
    }

    private void checkAttendanceHistoryByCrew(final Crews crews) {
        String nickname = inputView.readNickname();
        Crew crew = crews.findCrewByNickname(nickname);

        List<LocalDateTime> attendanceHistory = crew.getAttendanceHistory(getTodayDate());
        Map<AttendanceType, Integer> result = AttendanceType.countAttendanceType(attendanceHistory);
        SubjectType subjectType = SubjectType.from(result);

        resultView.printAttendanceHistoryResultByCrew(nickname, attendanceHistory, result, subjectType);
    }

    private void checkDismissalCrews(final Crews crews) {
        List<DismissalCrewDto> dtos = crews.findDismissalCrewDtos(getTodayDate());
        Collections.sort(dtos);
        resultView.printDismissalResult(dtos);
    }

    public static LocalDate getTodayDate() {
        return LocalDate.of(2024, 12, 19);
        //return LocalDate.now();
    }
}
