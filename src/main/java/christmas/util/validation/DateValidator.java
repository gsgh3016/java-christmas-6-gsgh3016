package christmas.util.validation;

import christmas.model.MyDate;

import java.time.format.DateTimeParseException;

public class DateValidator {
    private static final String EMPTY_DAY = "빈 날짜입니다.";
    private static final String NOT_NUMBER_DAY = "숫자만 입력해 주세요.";
    private static final String DAY_OUT_OF_BOUND = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static String checkDay(String target) {
        if (target.isEmpty()) {
            throw new IllegalStateException(EMPTY_DAY);
        }
        try {
            Integer.parseInt(target);
            new MyDate(target);
            return target;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_DAY);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(DAY_OUT_OF_BOUND);
        }
    }
}
