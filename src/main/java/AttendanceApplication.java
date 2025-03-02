import controller.AttendanceCheckConsumer;
import controller.AttendanceController;
import controller.AttendanceHistoryConsumer;
import controller.AttendanceModifyConsumer;
import controller.DismissalCrewCheckConsumer;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.BiConsumer;
import model.AttendanceBook;
import view.CommandInputView;
import view.InputValidator;
import view.InputView;
import view.ResultView;

public class AttendanceApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();

        CommandInputView commandInputView = new CommandInputView(scanner, inputValidator);
        InputView inputView = new InputView(scanner, inputValidator);
        ResultView resultView = new ResultView();

        BiConsumer<AttendanceBook, LocalDate>[] consumers = createConsumers(inputView, resultView);

        // TODO : AttendanceBook 객체 생성 로직 필요
        AttendanceController controller = new AttendanceController(commandInputView, consumers);

        controller.start(null);
    }

    private static BiConsumer<AttendanceBook, LocalDate>[] createConsumers(
            final InputView inputView,
            final ResultView resultView
    ) {
        return new BiConsumer[]{
                new AttendanceCheckConsumer(inputView, resultView),
                new AttendanceModifyConsumer(inputView, resultView),
                new AttendanceHistoryConsumer(inputView, resultView),
                new DismissalCrewCheckConsumer(resultView)
        };
    }
}
