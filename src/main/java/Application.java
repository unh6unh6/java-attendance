import controller.AttendanceController;
import java.util.HashMap;
import model.Campus;
import model.Crews;
import util.StringParser;
import view.InputValidator;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView(new InputValidator());
        ResultView resultView = new ResultView();
        StringParser stringParser = new StringParser();
        Campus campus = new Campus();

        AttendanceController controller = new AttendanceController(inputView, resultView, stringParser, campus);
        controller.start(
                new Crews(new HashMap<>())
        );
    }
}
