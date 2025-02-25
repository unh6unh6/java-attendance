package controller;

import dto.DismissalCrewDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import model.AttendanceType;
import model.Campus;
import model.Command;
import model.Crew;
import model.Crews;
import model.SubjectType;
import util.StringParser;
import view.InputView;
import view.ResultView;

public class AttendanceController {

    private final InputView inputView;
    private final ResultView resultView;
    private final Campus campus;

    private final Map<Command, Consumer<Crews>> commandExecutors = Map.of(
            Command.CHECK_ATTENDANCE, this::checkAttendance,
            Command.MODIFY_ATTENDANCE, this::modifyAttendance,
            Command.CHECK_ATTENDANCE_BY_CREW, this::checkAttendanceHistoryByCrew,
            Command.CHECK_DISMISSAL_CREW, this::checkDismissalCrews
    );

    public AttendanceController(final InputView inputView, final ResultView resultView, final Campus campus) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.campus = campus;
    }

    public static LocalDate getTodayDate() {
        return LocalDate.of(2024, 12, 13);
    }

    public void start(final Crews crews) {
        Command command = Command.from(inputView.readCommand(getTodayDate()));
        if (command.equals(Command.QUIT)) {
            return;
        }
        process(crews, command);
        start(crews);
    }

    private void process(final Crews crews, final Command command) {
        final Consumer<Crews> consumer = commandExecutors.get(command);
        consumer.accept(crews);
    }

    private void checkAttendance(final Crews crews) {
        LocalDate todayDate = getTodayDate();
        campus.validateOperationDate(todayDate);
        Crew crew = getCrew(crews, inputView.readNickname());
        LocalDateTime attendanceDateTime = getLocalDateTime(todayDate);
        campus.validateOperationTime(attendanceDateTime);
        crew.doAttendance(attendanceDateTime);
        resultView.printAttendanceHistory(
                attendanceDateTime, AttendanceType.from(attendanceDateTime));
    }

    private LocalDateTime getLocalDateTime(final LocalDate todayDate) {
        LocalTime attendanceTime = StringParser.parseLocalTime(inputView.readAttendanceTime());
        return LocalDateTime.of(todayDate, attendanceTime);
    }

    private void modifyAttendance(final Crews crews) {
        Crew crew = getCrew(crews, inputView.readModifyNickname());

        LocalDateTime modifyDateTime = getModifyLocalDateTime();
        campus.validateOperationTime(modifyDateTime);

        LocalDateTime previousTime = crew.doModify(modifyDateTime, getTodayDate());
        resultView.printModifyHistory(previousTime, AttendanceType.from(previousTime),
                LocalTime.from(modifyDateTime), AttendanceType.from(modifyDateTime));
    }

    private LocalDateTime getModifyLocalDateTime() {
        LocalDate modifyDate = StringParser.parseLocalDate(inputView.readModifyDay());
        campus.validateOperationDate(modifyDate);
        LocalTime modifyTime = StringParser.parseLocalTime(inputView.readModifyTime());
        return LocalDateTime.of(modifyDate, modifyTime);
    }

    private void checkAttendanceHistoryByCrew(final Crews crews) {
        String nickname = inputView.readNickname();
        Crew crew = getCrew(crews, nickname);

        List<LocalDateTime> attendanceHistory = crew.getAttendanceHistory(getTodayDate());
        Map<AttendanceType, Integer> result = AttendanceType.countAttendanceType(attendanceHistory);
        SubjectType subjectType = SubjectType.from(result);

        resultView.printAttendanceHistoryResultByCrew(nickname, attendanceHistory, result, subjectType);
    }

    private void checkDismissalCrews(final Crews crews) {
        List<DismissalCrewDto> dtos = crews.findDismissalCrewDtos(getTodayDate());
        crews.sortDismissalCrewDtos(dtos);
        resultView.printDismissalResult(dtos);
    }

    private Crew getCrew(final Crews crews, final String nickname) {
        return crews.findCrewByNickname(nickname);
    }
}
