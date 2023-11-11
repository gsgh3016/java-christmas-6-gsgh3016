package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputView {
    private static final String currentYearMonth = "2023-12-";
    public static LocalDate inputDate() {
        while (true) {
            try {
                String dateCommand = Console.readLine();
                return LocalDate.parse(currentYearMonth + dateCommand);
            } catch (DateTimeParseException dateTimeParseException) {
                ErrorView.printDateRange();
            } catch (IllegalArgumentException illegalArgumentException) {
                ErrorView.printOnlyNumber();
            }
        }
    }
}
