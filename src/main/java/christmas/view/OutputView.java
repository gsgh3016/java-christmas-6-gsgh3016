package christmas.view;

import christmas.model.Menu;
import christmas.util.Formatting;

public class OutputView {
    public static void printBookingQuestion() {
        System.out.println(
                "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n" +
                "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"
        );
    }

    public static void printMenuQuestion() {
        System.out.println(
                "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"
        );
    }

    public static void printEventBenefits(int day) {
        System.out.printf(
                "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", day
        );
    }

    public static void printBeforeDiscount(int totalPrice) {
        System.out.printf(Formatting.PRICE + "\n", totalPrice);
    }

    public static void printCategory(String orderedMenu) {
        System.out.println(orderedMenu);
    }

    public static void printOrderDetail(Menu menu, int quantity) {
        System.out.printf(Formatting.MENU + "\n", menu.getName(), quantity);
    }

    public static void printResultDetail(String detail) {
        System.out.println(detail);
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
