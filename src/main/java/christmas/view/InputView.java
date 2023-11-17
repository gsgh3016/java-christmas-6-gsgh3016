package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.util.OrderParser;
import christmas.util.validation.TotalOrderValidator;

import java.util.EnumMap;
import java.util.NoSuchElementException;

import static christmas.util.validation.DateValidator.checkDay;

public class InputView {
    public MyDate inputDate() {
        while (true) {
            try {
                String input = Console.readLine();
                return new MyDate(checkDay(input));
            } catch (NoSuchElementException ignored){
                Console.readLine();
            } catch (Exception e) {
                ErrorView.print(e.getMessage());
            }
        }
    }

    public EnumMap<Menu, Integer> inputOrder() {
        while (true) {
            try {
                TotalOrderValidator validator = new TotalOrderValidator();
                String input = Console.readLine();
                return OrderParser.parse(validator.checkTotalOrder(input));
            } catch (NoSuchElementException ignored){
                Console.readLine();
            } catch (Exception e) {
                ErrorView.print(e.getMessage());
            }
        }
    }
}
