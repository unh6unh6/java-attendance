package controller;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;
import model.AttendanceBook;
import view.CommandInputView;

public class AttendanceController {

    private final CommandInputView commandInputView;
    private final Map<Command, BiConsumer<AttendanceBook, LocalDate>> commandByBiConsumer
            = new EnumMap<>(Command.class);

    public AttendanceController(
            final CommandInputView commandInputView,
            final AttendanceCheckConsumer attendanceCheckConsumer,
            final AttendanceModifyConsumer attendanceModifyConsumer
    ) {
        this.commandInputView = commandInputView;
        commandByBiConsumer.put(Command.CHECK_ATTENDANCE, attendanceCheckConsumer);
        commandByBiConsumer.put(Command.MODIFY_ATTENDANCE, attendanceModifyConsumer);
        commandByBiConsumer.put(Command.QUIT, (system, exit) -> System.exit(0));
    }

    public void start(final AttendanceBook attendanceBook) {
        while (true) {
            LocalDate todayDate = getTodayDate();
            Command command = Command.from(commandInputView.getCommand(todayDate));
            commandByBiConsumer.get(command).accept(attendanceBook, todayDate);
        }
    }

    private LocalDate getTodayDate() {
        return LocalDate.now();
    }
}
