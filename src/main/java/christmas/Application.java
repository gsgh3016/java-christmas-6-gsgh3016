package christmas;

import christmas.service.Service;

public class Application {
    public static void main(String[] args) {
        Service service = new Service();
        service.start();
    }
}
