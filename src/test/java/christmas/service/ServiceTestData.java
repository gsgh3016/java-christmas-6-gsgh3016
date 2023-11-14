package christmas.service;

import christmas.model.Menu;
import christmas.model.MyDate;

import java.util.EnumMap;

public class ServiceTestData {
    public EnumMap<Menu, Integer> orders = new EnumMap<>(Menu.class);
    public MyDate date;

    public ServiceTestData() {
        date = new MyDate("15");
        orders.put(Menu.CHOCOLATE_CAKE, 2);
        orders.put(Menu.BARBECUE_RIBS, 1);
    }
}
