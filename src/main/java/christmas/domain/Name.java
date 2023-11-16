package christmas.domain;

import christmas.exception.ErrorMessage;

public class Name {
    private final String value;

    private Name(String name) {
        validateNameInMenu(name);
        this.value = name;
    }

    public static Name from(String name) {
        return new Name(name);
    }

    public String getValue() {
        return value;
    }

    private void validateNameInMenu(String name) {
        if (!Menu.has(name)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }
}
