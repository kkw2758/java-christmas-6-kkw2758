package christmas.exception;

import java.util.function.Function;

public enum ErrorMessage {
    DEFAULT("에러메시지", IllegalArgumentException::new);

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
