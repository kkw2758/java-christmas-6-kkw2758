package christmas.domain;


import christmas.exception.ErrorMessage;
import christmas.utils.Validator;

public class Count {
    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 20;
    private final int value;

    private Count(int count) {
        this.value = count;
    }

    public static Count from(int count) {
        Validator.validateInRange(count, MIN_COUNT, MAX_COUNT, ErrorMessage.INVALID_ORDERS);
        return new Count(count);
    }

    public int getValue() {
        return value;
    }
}
