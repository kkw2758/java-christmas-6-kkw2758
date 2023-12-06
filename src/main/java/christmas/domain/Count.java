package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.utils.Validator;

public class Count {

    private final int value;

    private Count(int count) {
        value = count;
    }

    public static Count from(int count) {
        Validator.validatePositiveNumber(count, ErrorMessage.INVALID_ORDERS);
        return new Count(count);
    }

    public int getValue() {
        return value;
    }
}
