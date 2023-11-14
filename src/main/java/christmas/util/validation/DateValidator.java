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
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER_DAY);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(ErrorMessage.DAY_OUT_OF_BOUND);
        }
    }
}
