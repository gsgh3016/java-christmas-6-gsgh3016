package christmas.model;

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

    public static DiscountManager getInstance() { return instance; }

    public static void add(String discountCategory, String discountDetail) {
        getInstance().discountContents
                .computeIfAbsent(discountCategory, k -> new ArrayList<>())
                .add(discountDetail);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, List<String>> discountContent: discountContents.entrySet()) {
            parseCategory(discountContent, stringBuilder);
        }
        return stringBuilder.toString();
    }

    private void parseCategory(Map.Entry<String, List<String>> discountContent, StringBuilder stringBuilder) {
        String category = discountContent.getKey();
        List<String> details = discountContent.getValue();
        stringBuilder.append(category + "\n");
        stringBuilder.append(parseDetail(details) + "\n");
    }

    private String parseDetail(List<String> details) {
        return String.join("\n", details.toArray(new String[0]));
    }
}
