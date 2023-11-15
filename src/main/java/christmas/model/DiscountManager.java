package christmas.model;

import christmas.util.constant.Formatting;
import christmas.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountManager {
    private static final DiscountManager instance = new DiscountManager();
    private final Map<String, List<String>> discountContents;

    private DiscountManager() {
        discountContents = new HashMap<>();
    }

    public static DiscountManager getInstance() {
        return instance;
    }

    public static void add(String discountCategory, String discountDetail) {
        getInstance().discountContents
                .computeIfAbsent(discountCategory, k -> new ArrayList<>())
                .add(discountDetail);
    }

    public void printResult() {
        for (String category: Formatting.DISCOUNT_ORDERING) {
            List<String> details = findByCategory(category);
            printCategory(category, details);
        }
    }

    private void printCategory(String category, List<String> details) {
        OutputView.printCategory(category);
        printDetail(details);
        OutputView.printEmptyLine();
    }

    private void printDetail(List<String> details) {
        for (String detail : details) {
            OutputView.printResultDetail(detail);
        }
    }

    public static List<String> findByCategory(String category) {
        return DiscountManager.instance.discountContents.get(category);
    }
}
