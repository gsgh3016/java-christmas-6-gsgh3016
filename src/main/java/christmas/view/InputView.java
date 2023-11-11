package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.MyDate;
import christmas.util.Validator;

import java.time.format.DateTimeParseException;

public class InputView {
    public static MyDate inputDate() {
        while (true) {
            try {
                String dateCommand = Validator.checkNumber(Console.readLine());
                return new MyDate(dateCommand);
            } catch (DateTimeParseException dateTimeParseException) {
                ErrorView.printDateRange();
            } catch (IllegalArgumentException illegalArgumentException) {
                ErrorView.printOnlyNumber();
            }
        }
    }
}
