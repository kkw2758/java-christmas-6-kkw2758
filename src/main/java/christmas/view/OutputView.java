package christmas.view;


public class OutputView {
    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return instance;
    }

    public static void printlnMessage(String message) {
        System.out.println(message);
    }

    public static void printlnFormat(String message, Object... args) {
        printlnMessage(String.format(message, args));
    }

    public static void printFormat(String message, Object... args) {
        printMessage(String.format(message, args));
    }

    public static void printMessage(String message) {
        System.out.print(message);
    }
}
