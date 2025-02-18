package view;

import java.util.Scanner;

public class InputView {
    private static final String NICKNAME_INPUT_GUIDE = "닉네임을 입력해 주세요.";

    private final InputValidator inputValidator;

    public InputView(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String readNickname() {
        String nickname = readInput();
        System.out.println(NICKNAME_INPUT_GUIDE);
        return nickname;
    }

    public String readAttendanceTime() {
        String attendanceTime = readInput();
        System.out.println(NICKNAME_INPUT_GUIDE);
        return attendanceTime;
    }

    private String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        inputValidator.validateIsNullOrEmpty(input);
        return input;
    }
}
