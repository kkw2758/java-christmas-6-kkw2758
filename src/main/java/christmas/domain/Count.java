package christmas.domain;

import christmas.exception.ErrorMessage;

public class Count {
    private final int MIN_ORDER_COUNT = 1;

    private final int count;

    private Count(int count) {
        validateCount(count);
        this.count = count;
    }

    public static Count from(int count) {
        return new Count(count);
    }

    public int getCount() {
        return count;
    }

    private void validateCount(int count) {
        if (!checkCountInRange(count)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }

    private boolean checkCountInRange(int count) {
        return count >= MIN_ORDER_COUNT;
    }
}
