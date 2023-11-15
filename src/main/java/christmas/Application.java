package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.ChristmasController;

public class Application {
    public static void main(String[] args) {
        new ChristmasController().run();
        Console.close();
    }
}
