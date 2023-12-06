package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.utils.Validator;

public class VisitDate {
    private static final int MIN_VISIT_DATE = 1;
    private static final int MAX_VISIT_DATE = 31;

    private final int visitDate;

    private VisitDate(int visitDate) {
        this.visitDate = visitDate;
    }

    public static VisitDate from(int visitDate) {
        validateVisitDate(visitDate);
        return new VisitDate(visitDate);
    }

    private static void validateVisitDate(int visitDate) {
        Validator.validateInRange(visitDate, MIN_VISIT_DATE, MAX_VISIT_DATE, ErrorMessage.INVALID_VISIT_DATE_INPUT);
    }

    public int getVisitDate() {
        return visitDate;
    }
}
