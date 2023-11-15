package christmas.util.validation;

import christmas.model.MyDate;

import java.time.format.DateTimeParseException;

public class DateValidator {
    public static String checkDay(String target) {
        if (target.isEmpty()) {
            throw new IllegalStateException(ErrorMessage.EMPTY_DAY);
        }
        try {
            Integer.parseInt(target);
            new MyDate(target);
            return target;
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DAY);
        }
    }
}
