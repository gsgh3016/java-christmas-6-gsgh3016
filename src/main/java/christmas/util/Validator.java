package christmas.util;

public class Validator {
    public static String checkNumber(String target) {
        try {
            return String.valueOf(
                    Integer.parseInt(target)
            );
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException();
        }
    }
}
