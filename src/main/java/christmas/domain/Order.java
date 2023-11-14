package christmas.domain;


import christmas.exception.ErrorMessage;

public class Order {
    private static final String NATURAL_NUMBER_REGULAR_EXPRESSION = "\\d+";
    private static final String DELIMITER = "-";
    private static final int ORDER_INPUT_LENGTH = 2;
    private final Count count;
    private final Name name;

    private Order(String[] order) {
        validateOrder(order);
        this.name = Name.from(order[0].trim());
        this.count = Count.from(Integer.parseInt(order[1].trim()));
    }

    public static Order of(String order) {
        return new Order(order.split(DELIMITER));
    }

    private static void validateOrder(String[] order) {
        validateOrderInputLength(order);
        validateOrderInputFormat(order);
    }

    private static void validateOrderInputFormat(String[] order) {
        if (isNotNaturalNumber(order[1])) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }

    private static void validateOrderInputLength(String[] order) {
        if (!checkOrderInputLength(order)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }

    private static boolean checkOrderInputLength(String[] order) {
        return order.length == ORDER_INPUT_LENGTH;
    }
    private static boolean isNotNaturalNumber(final String order) {
        return !order.matches(NATURAL_NUMBER_REGULAR_EXPRESSION);
    }

    public Count getCount() {
        return count;
    }

    public Name getName() {
        return name;
    }
}
