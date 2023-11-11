package christmas.domain;

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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
