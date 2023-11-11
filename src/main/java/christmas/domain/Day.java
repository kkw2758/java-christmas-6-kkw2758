package christmas.domain;

import christmas.exception.ErrorMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Day {
    private static final int MIN_DAY_OF_MONTH = 1;
    private static final int MAX_DAY_OF_MONTH = 31;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final List<Integer> starDays = new ArrayList<>(List.of(3, 10, 17, 24, 25, 31));
    private static final List<Integer> weekend = new ArrayList<>(List.of(5, 6));
    private static final Map<Integer, Day> dayCache = new HashMap<>();

    static {
        initDayCache();
    }

    private final LocalDate date;

    private Day(int dayOfMonth) {
        this.date = LocalDate.of(YEAR, MONTH, dayOfMonth);
    }

    public static Day of(int dayOfMonth) {
        validateDayOfMonth(dayOfMonth);
        return dayCache.get(dayOfMonth);
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isWeekend() {
        return weekend.contains(date.getDayOfWeek().getValue());
    }

    private static void validateDayOfMonth(int dayOfMonth) {
        if (!isDayOfMonthInRange(dayOfMonth)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY_OF_MONTH_ERROR.getMessage());
        }
    }

    private static boolean isDayOfMonthInRange(int dayOfMonth) {
        return dayOfMonth >= MIN_DAY_OF_MONTH && dayOfMonth <= MAX_DAY_OF_MONTH;
    }

    private static void initDayCache() {
        IntStream.range(MIN_DAY_OF_MONTH, MAX_DAY_OF_MONTH + 1)
                .forEach(i -> dayCache.put(i, new Day(i, isStarDay(i))));
    }

    private static boolean isStarDay(int dayOfMonth) {
        return starDays.contains(dayOfMonth);
    }
}
