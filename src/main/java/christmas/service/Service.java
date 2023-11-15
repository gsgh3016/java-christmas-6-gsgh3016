package christmas.service;

import christmas.model.Menu;
import christmas.model.MyDate;
import christmas.model.DiscountManager;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.EnumMap;

public class Service {
    private MyDate date;
    private EnumMap<Menu, Integer> orders;
    private OrderPriceService orderService;
    private PaymentService paymentService;
    private int totalPrice;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        input();
        order();
        print();
    }

    private void input() {
        outputView.printBookingQuestion();
        date = inputView.inputDate();
        outputView.printMenuQuestion();
        orders = inputView.inputOrder();
        outputView.printEventBenefits(date.getDayOfMonth());
        orderService = new OrderPriceService(orders);
    }

    private void order() {
        totalPrice = orderService.getTotalPrice();
        paymentService = new PaymentService(date, orders);
        paymentService.pay(totalPrice);
    }

    private void print() {
        outputView.printOrders(orders, totalPrice);
        outputView.printDiscount();
    }
}
