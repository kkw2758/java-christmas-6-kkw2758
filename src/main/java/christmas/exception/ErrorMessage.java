package christmas.exception;

import java.util.function.Function;

public enum ErrorMessage {
    INVALID_VISIT_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요.", IllegalArgumentException::new),
    INVALID_ORDERS("유효하지 않은 주문입니다. 다시 입력해 주세요.", IllegalArgumentException::new);

    private final String message;

    private final Function<String, ? extends RuntimeException> constructorOfException;
    private static final String PREFIX = "[ERROR] ";

    ErrorMessage(String message, Function<String, ? extends RuntimeException> constructorOfException) {
        this.message = PREFIX + message;
        this.constructorOfException = constructorOfException;
    }

    public RuntimeException generateException() {
        return this.constructorOfException.apply(this.message);
    }

    public void throwException() {
        throw generateException();
    }
}
