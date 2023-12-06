package christmas.domain;

import christmas.exception.ErrorMessage;
import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5_500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 5_500, MenuCategory.APPETIZER),
    T_BONE_STEAK("티본스테이크", 55_500, MenuCategory.MAIN),
    BBQ_LIP("바비큐립", 54_500, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35_500, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25_500, MenuCategory.MAIN),
    CHOCOLATE_CAKE("초코케이크", 15_500, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", 25_500, MenuCategory.DESSERT),
    ZERO_COLA("제로콜라", 3_500, MenuCategory.DRINK),
    RED_WINE("레드와인", 60_000, MenuCategory.DRINK),
    CHAMPAGNE("샴페인", 25_500, MenuCategory.DRINK);

    private final String name;
    private final int price;
    private final MenuCategory category;

    Menu(String name, int price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static Menu from(String name){
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(name))
                .findAny()
                .orElseThrow(ErrorMessage.INVALID_ORDERS::generateException);
    }

    public MenuCategory getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
}
