package christmas.utils;

import christmas.exception.ErrorMessage;
import java.util.List;

public class Validator {
    private static final String NATURAL_NUMBER_REGULAR_EXPRESSION = "\\d+";

    public static void validateBlankInput(String message, ErrorMessage e) {
        if (message.isBlank()) {
            e.throwException();
        }
    }

    /**
     * 0~9 중 하나로 이루어져 있지 않다면 예외 발생
     */
    public static void validateNumberFormat(final String target, ErrorMessage e) {
        if (!target.matches(NATURAL_NUMBER_REGULAR_EXPRESSION)) {
            e.throwException();
        }
    }

    /**
     * 입력 받은 정수가 양수가 아니라면 예외 발생
     */
    public static void validatePositiveNumber(final int target, final ErrorMessage e) {
        if (target <= 0) {
            e.throwException();
        }
    }

    public static void validateNoRemainders(int target, int number, ErrorMessage e) {
        if (target % number != 0) {
            e.throwException();
        }
    }

    /**
     * 리스트에 중복된 요소가 있다면 예외 발생
     */
    public static void validateNoDuplicates(final List<?> target, ErrorMessage e) {
        long count = target.stream()
                .distinct()
                .count();

        if (count != target.size()) {
            e.throwException();
        }
    }

    /**
     * 문자열에 중복된 문자가 있다면 예외 발생
     */
    public static void validateNoDuplicates(final String target, ErrorMessage e) {
        validateNoDuplicates(Converter.convertToList(target), e);
    }


    /**
     * 리스트 길이가 최대값보다 크다면 예외 발생
     */
    public static void validateSizeLimit(final List<?> target, final int maximumSize, final ErrorMessage e) {
        if (target.size() > maximumSize) {
            e.throwException();
        }
    }

    /**
     * 문자열 길이가 최대값보다 크다면 예외 발생
     */
    public static void validateSizeLimit(final String target, final int maximumSize, final ErrorMessage e) {
        validateSizeLimit(Converter.convertToList(target), maximumSize, e);
    }

    /**
     * 리스트 길이가 유효 사이즈와 다르다면 예외 발생
     */
    public static void validateSize(
            final List<?> target,
            final int validSize,
            final ErrorMessage e
    ) {
        if (target.size() != validSize) {
            e.throwException();
        }
    }

    /**
     * 문자열 길이가 유효 사이즈와 다르다면 예외 발생
     */
    public static void validateSize(
            final String target,
            final int validSize,
            final ErrorMessage e
    ) {
        validateSize(Converter.convertToList(target), validSize, e);
    }

    /**
     * 주어진 숫자가 최대값보다 크거나 최솟값보다 작다면 예외 발생
     */
    public static void validateInRange(
            final int target,
            final int minimumSize,
            final int maximumSize,
            final ErrorMessage e
    ) {
        if (maximumSize < target || target < minimumSize) {
            e.throwException();
        }
    }

    /**
     * 리스트 길이가 최대값보다 크거나 최솟값보다 작다면 예외 발생
     */
    public static void validateInRange(
            final List<?> target,
            final int minimumSize,
            final int maximumSize,
            final ErrorMessage e
    ) {
        validateInRange(
                target.size(),
                minimumSize,
                maximumSize,
                e
        );
    }

    /**
     * 문자열 길이가 최대값보다 크거나 최솟값보다 작다면 예외 발생
     */
    public static void validateInRange(
            final String target,
            final int minimumSize,
            final int maximumSize,
            final ErrorMessage e
    ) {
        validateInRange(
                target.length(),
                minimumSize,
                maximumSize,
                e
        );
    }

    /**
     * 리스트가 오직 특정한 요소들로 이루어져 있는지 확인. 만약 특정한 요소 외의 것이 포함되어 있다면 예외 발생.
     *
     * @param target        검증할 리스트
     * @param validElements 유효한 요소 요소
     */
    public static <E> void validateOnlySpecificElements(List<E> target, List<E> validElements, ErrorMessage e) {
        target.forEach(element -> validateHasElement(validElements, element, e));
    }

    /**
     * 특정 문자열이 정해진 문자들로만 이루어져 있지 않다면 예외 발생
     *
     * @param target        검증 대상 문자열
     * @param validElements 유효한 구성 문자 리스트
     */
    public static void validateOnlySpecificElements(String target, List<Character> validElements, ErrorMessage e) {
        validateOnlySpecificElements(
                Converter.convertToList(target),
                validElements.stream()
                        .map(String::valueOf)
                        .toList(),
                e
        );
    }

    /**
     * 검증 대상이 특정 요소를 특정 개수보다 많이 포함하고 있다면 예외 발생
     *
     * @param target       검증 대상 리스트
     * @param element      개수를 확인할 특정 요소
     * @param maximumCount 최대 허용 개수
     */
    public static <E> void validateMaxElementCount(List<E> target, E element, long maximumCount, ErrorMessage e) {
        long countOfElementInTarget = target.stream()
                .filter(element::equals)
                .count();

        if (countOfElementInTarget > maximumCount) {
            e.throwException();
        }
    }

    /**
     * 문자열이 특정 요소를 특정 개수보다 많이 포함하고 있다면 예외 발생.
     *
     * @param target       검증 대상 문자열
     * @param element      개수를 확인할 특정 요소
     * @param maximumCount 최대 허용 개수
     */
    public static void validateMaxElementCount(String target, char element, long maximumCount, ErrorMessage e) {
        validateMaxElementCount(
                Converter.convertToList(target),
                String.valueOf(element),
                maximumCount,
                e
        );
    }

    /**
     * 특정 요소가 포함되어 있지 않다면 예외가 발생. List.contains()를 사용한다.
     */
    public static <E> void validateHasElement(List<E> target, E element, ErrorMessage e) {
        if (!target.contains(element)) {
            e.throwException();
        }
    }

    /**
     * 문자열에 특정 문자가 포함 되어 있지 않다면 예외가 발생. List.contains()를 사용한다.
     */
    public static void validateHasElement(String target, Character element, ErrorMessage e) {
        validateHasElement(
                Converter.convertToList(target),
                String.valueOf(element),
                e
        );
    }

    /**
     * 문자열에 특정 문자열이 포함되어 있지 않다면 예외가 발생. List.contains()를 사용한다.
     */
    public static void validateContainString(String target, String element, ErrorMessage e) {
        if (!target.contains(element)) {
            e.throwException();
        }
    }
}
