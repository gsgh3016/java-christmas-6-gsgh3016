package christmas.util.validation;

public record ErrorMessage() {
    public static final String EMPTY_DAY = "빈 날짜입니다.";
    public static final String INVALID_DAY = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public static final String INVALID_ORDER = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String EMPTY_ORDER = "빈 주문입니다.";

    public static final String DISCOUNT_ERROR = "할인 금액이 주문 금액보다 큽니다.";
}
