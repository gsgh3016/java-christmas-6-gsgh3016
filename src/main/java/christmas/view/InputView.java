package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.util.OrderParser;

import java.util.EnumMap;

import static christmas.util.DateValidator.checkDay;
import static christmas.util.TotalOrderValidator.checkTotalOrder;

public class InputView {
    public static MyDate inputDate() {
        while (true) {
            try {
                return new MyDate(checkDay(Console.readLine()));
            } catch (Exception e) {
                ErrorView.print(e.getMessage());
            }
        }
    }

    public static EnumMap<Menu, Integer> inputOrder() {
        while (true) {
            try {
                return OrderParser.parse(checkTotalOrder(Console.readLine()));
            } catch (Exception e) {
                ErrorView.print(e.getMessage());
            }
        }
    }
}
