package view;

import java.util.Scanner;

public class InputView {
    private static final String NICKNAME_INPUT_GUIDE = "닉네임을 입력해 주세요.";
    private static final String ATTENDANCE_TIME_INPUT_GUIDE = "등교 시간을 입력해 주세요.";
    private static final String MODIFY_NICKNAME_INPUT_GUIDE = "출석을 수정하려는 크루의 닉네임을 입력해 주세요.";
    private static final String MODIFY_DAY_INPUT_GUIDE = "수정하려는 날짜(일)를 입력해 주세요.";
    private static final String MODIFY_TIME_INPUT_GUIDE = "언제로 변경하겠습니까?";

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

    public String readModifyNickname() {
        System.out.println(MODIFY_NICKNAME_INPUT_GUIDE);
        return readInput();
    }

    public String readModifyTime() {
        System.out.println(MODIFY_TIME_INPUT_GUIDE);
        return readInput();
    }

    public String readModifyDay() {
        System.out.println(MODIFY_DAY_INPUT_GUIDE);
        return readInput();
    }

    private String readInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        inputValidator.validateIsNullOrEmpty(input);
        return input;
    }
}
