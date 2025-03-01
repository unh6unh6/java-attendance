import controller.AttendanceCheckConsumer;
import controller.AttendanceController;
import controller.AttendanceModifyConsumer;
import java.util.Scanner;
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

        AttendanceCheckConsumer attendanceCheckConsumer = new AttendanceCheckConsumer(inputView, resultView);
        AttendanceModifyConsumer attendanceModifyConsumer = new AttendanceModifyConsumer(inputView, resultView);
        // TODO : AttendanceBook 객체 생성 로직 필요
        AttendanceController controller = new AttendanceController(
                commandInputView,
                attendanceCheckConsumer,
                attendanceModifyConsumer
        );

        controller.start(null);
    }
}
