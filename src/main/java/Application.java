import controller.AttendanceController;
import util.StringParser;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        StringParser stringParser = new StringParser();

        AttendanceController controller = new AttendanceController(inputView, resultView, stringParser);
        controller.start();
    }
}
