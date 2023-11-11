package christmas.domain;

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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean checkCountInRange(int count) {
        return count >= MIN_ORDER_COUNT;
    }
}
