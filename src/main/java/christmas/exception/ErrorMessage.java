package christmas.exception;

public enum ErrorMessage {
    INVALID_ORDER_INPUT_ERROR("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    ONLY_DRINK_ORDER_ERROR("음료만 주문시, 주문할 수 없습니다."),
    MAX_MENU_COUNT_ERROR("메뉴는 한번에 최대 %d개까지만 주문할 수 있습니다."),
    NAME_NOT_IN_MENU_ERROR("입력하신 메뉴는 메뉴판에 없습니다."),
    INVALID_DAY_OF_MONTH_ERROR("유효하지 않은 날짜입니다. 다시 입력해 주세요.");

    private final String message;
    private final String PREFIX = "[ERROR] ";
    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}
