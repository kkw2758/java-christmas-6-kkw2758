package christmas.domain;

import christmas.exception.ErrorMessage;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6000, Category.APPETIZER),
    TAPAS("타파스", 5500, Category.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, Category.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, Category.MAIN),
    BBQ_RIB("바비큐립", 54000, Category.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, Category.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, Category.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, Category.DESSERT),
    ICE_CREAM("아이스크림", 5000, Category.DESSERT),
    ZERO_COLA("제로콜라", 3000, Category.DRINK),
    RED_WINE("레드와인", 60000, Category.DRINK),
    CHAMPAGNE("샴페인", 25000, Category.DRINK);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public static boolean has(String menu) {
        return Arrays.stream(Menu.values())
                .map(Menu::getName)
                .anyMatch(menu::equals);
    }

    public static Menu findMenuByName(String name) {
        return Arrays.stream(Menu.values())
                .filter((menu) -> menu.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.NAME_NOT_IN_MENU_ERROR.getMessage()));
    }
}
