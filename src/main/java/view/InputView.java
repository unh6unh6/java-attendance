package view;

import java.util.Scanner;

public class InputView {
    private static final String NICKNAME_INPUT_GUIDE = "닉네임을 입력해 주세요.";
    private static final String ATTENDANCE_TIME_INPUT_GUIDE = "등교 시간을 입력해 주세요.";

    private final InputValidator inputValidator;

    public InputView(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String readNickname() {
        System.out.println(NICKNAME_INPUT_GUIDE);
        return readInput();
    }

    public String readAttendanceTime() {
        System.out.println(ATTENDANCE_TIME_INPUT_GUIDE);
        return readInput();
    }

    private String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        inputValidator.validateIsNullOrEmpty(input);
        return input;
    }
}
