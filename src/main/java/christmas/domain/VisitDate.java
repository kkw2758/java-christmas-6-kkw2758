package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.utils.Validator;

public class VisitDate {
    private static final int MIN_VISIT_DATE = 1;
    private static final int MAX_VISIT_DATE = 31;

    private final int value;

    private VisitDate(int visitDate) {
        this.value = visitDate;
    }

    public static VisitDate from(int visitDate) {
        Validator.validateInRange(visitDate, MIN_VISIT_DATE, MAX_VISIT_DATE, ErrorMessage.INVALID_VISIT_DATE);
        return new VisitDate(visitDate);
    }

    public int getValue() {
        return value;
    }
}
