package christmas.domain;

import christmas.exception.ErrorMessage;

public class Name {

    private final String name;

    private Name(String name) {
        validateNameInMenu(name);
        this.name = name;
    }

    public static Name from(String name) {
        return new Name(name);
    }

    public String getName(){
        return name;
    }
    private void validateNameInMenu(String name) {
        if (!Menu.has(name)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_INPUT_ERROR.getMessage());
        }
    }
}
