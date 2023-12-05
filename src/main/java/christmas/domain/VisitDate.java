package christmas.domain;

import christmas.exception.ErrorMessage;

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
        if (checkVisitDateNotInRange(visitDate)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_VISIT_DATE_INPUT.getMessage());
        }
    }

    private static boolean checkVisitDateNotInRange(int visitDate) {
        return visitDate < MIN_VISIT_DATE || visitDate > MAX_VISIT_DATE;
    }

    public int getVisitDate() {
        return visitDate;
    }
}
