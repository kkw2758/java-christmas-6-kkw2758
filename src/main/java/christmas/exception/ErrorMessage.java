package christmas.exception;

public enum ErrorMessage {
    INVALID_VISIT_DATE_INPUT("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final String message;
    private final String PREFIX = "[ERROR] ";

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
