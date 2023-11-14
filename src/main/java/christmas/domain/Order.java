package christmas.domain;


import christmas.exception.ErrorMessage;

public class Order {
    private static final String NATURAL_NUMBER_REGULAR_EXPRESSION = "\\d+";
    private final Count count;
    private final Name name;

    private Order(String[] order) {
        validateOrder(order);
        this.name = Name.from(order[0].trim());
        this.count = Count.from(Integer.parseInt(order[1].trim()));
    }

    public static Order of(String order) {
        return new Order(order.split("-"));
    }

    private static void validateOrder(String[] orderInput) {
        validateOrderInputLength(orderInput);
        validateOrderInputFormat(orderInput);
    }

    private static void validateOrderInputFormat(String[] orderInput) {
        if (isNotNaturalNumber(orderInput[1])) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }

    private static void validateOrderInputLength(String[] orderInput) {
        if (!checkOrderInputLength(orderInput)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }

    private static boolean checkOrderInputLength(String[] orderInput) {
        return orderInput.length == 2;
    }
    private static boolean isNotNaturalNumber(final String userInput) {
        return !userInput.matches(NATURAL_NUMBER_REGULAR_EXPRESSION);
    }

    public Count getCount() {
        return count;
    }

    public Name getName() {
        return name;
    }
}
