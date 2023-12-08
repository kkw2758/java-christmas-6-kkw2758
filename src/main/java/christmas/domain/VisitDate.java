package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.utils.Validator;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDate {
    private static final int MIN_VISIT_DATE = 1;
    private static final int MAX_VISIT_DATE = 31;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final List<DayOfWeek> WEEKEND = List.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final int CHRISTMAS = 25;

    private final int value;

    private VisitDate(int visitDate) {
        this.value = visitDate;
    }

    public static VisitDate from(int visitDate) {
        Validator.validateInRange(visitDate, MIN_VISIT_DATE, MAX_VISIT_DATE, ErrorMessage.INVALID_VISIT_DATE);
        return new VisitDate(visitDate);
    }

    public boolean isWeekend() {
        LocalDate date = LocalDate.of(YEAR, MONTH, value);
        return WEEKEND.contains(date.getDayOfWeek());
    }

    public boolean isWeekday() {
        LocalDate date = LocalDate.of(YEAR, MONTH, value);
        return !WEEKEND.contains(date.getDayOfWeek());
    }

    public boolean isStarDay() {
        return value == CHRISTMAS || LocalDate.of(YEAR, MONTH, value).getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public boolean isChristmasEventTarget() {
        return value <= CHRISTMAS;
    }

    public int getValue() {
        return value;
    }
}
