package christmas.view;

import christmas.model.DiscountManager;
import christmas.model.Menu;
import christmas.util.constant.Category;
import christmas.util.constant.Formatting;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printBookingQuestion() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
    }

    public void printMenuQuestion() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }

    public void printEventBenefits(int day) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", day);
    }

    public void printOrders(EnumMap<Menu, Integer> orders, int totalPrice) {
        printEmptyLine();
        printCategory(Category.ORDERED_MENU);
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            printOrderDetail(order.getKey(), order.getValue());
        }
        printEmptyLine();

        printCategory(Category.TOTAL_PRICE_BEFORE_DISCOUNT);
        printBeforeDiscount(totalPrice);
        printEmptyLine();
    }

    public void printBeforeDiscount(int totalPrice) {
        System.out.printf(Formatting.PRICE + "\n", totalPrice);
    }

    public void printCategory(String orderedMenu) {
        System.out.println(orderedMenu);
    }

    public void printOrderDetail(Menu menu, int quantity) {
        System.out.printf(Formatting.MENU + "\n", menu.getName(), quantity);
    }

    public void printDiscount() {
        for (String category: Formatting.DISCOUNT_ORDERING) {
            List<String> details = DiscountManager.findByCategory(category);
            printCategoryAndDetails(category, details);
        }
    }


    private void printCategoryAndDetails(String category, List<String> details) {
        printCategory(category);
        printDetails(details);
        printEmptyLine();
    }

    private void printDetails(List<String> details) {
        for (String detail : details) {
            printSingleDetail(detail);
        }
    }

    public static void printSingleDetail(String detail) {
        System.out.println(detail);
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
