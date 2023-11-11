package christmas.view;

public class ErrorView {
    private static final String ERROR = "[ERROR] ";

    public static void printOnlyNumber() {
        System.out.println(ERROR + "숫자만 입력해 주세요.");
    }

    public static void printDateRange() {
        System.out.println(ERROR + "유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}
