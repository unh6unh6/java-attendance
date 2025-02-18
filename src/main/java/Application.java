import controller.AttendanceController;
import view.InputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();

        AttendanceController controller = new AttendanceController(inputView);
        controller.start();
    }
}
