package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.ErrorMessage;
import christmas.utils.Validator;

public class Inputview {
    private static final Inputview instance = new Inputview();

    private Inputview() {
    }

    public static Inputview getInstance() {
        return instance;
    }

    public static String enterMessage(ErrorMessage e) {
        String userInput = Console.readLine();
        Validator.validateBlankInput(userInput, e);
        return userInput;
    }
}
