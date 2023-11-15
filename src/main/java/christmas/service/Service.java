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

    public void start() {
        input();
        order();
        print();
    }

    private void input() {
        OutputView.printBookingQuestion();
        date = InputView.inputDate();
        OutputView.printMenuQuestion();
        OutputView.printEventBenefits(date.getDayOfMonth());
        orders = InputView.inputOrder();
        orderService = new OrderPriceService(orders);
    }

    private void order() {
        totalPrice = orderService.getTotalPrice();
        orderService.printOrders();
        paymentService = new PaymentService(date, orders);
        paymentService.pay(totalPrice);
    }

    private void print() {
        DiscountManager.getInstance().printResult();
    }
}
