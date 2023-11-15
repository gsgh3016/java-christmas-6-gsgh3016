package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

public class MyDate {
    private static final String CURRENT_YEAR_MONTH = "2023-12-";
    private static final int DAY_FORMAT_LENGTH = 2;
    private static final String DAY_DIGIT_FORMAT = "0";

    private final int CHRISTMAS = 25;

    private final LocalDate date;

    public MyDate(String date) {
        if (date.length() < DAY_FORMAT_LENGTH) {
            date = DAY_DIGIT_FORMAT + date;
        }
        this.date = LocalDate.parse(CURRENT_YEAR_MONTH + date);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(date);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object instanceof LocalDate) {
            return this.date.equals(object);
        }
        if (object instanceof MyDate) {
            return this.date.equals(((MyDate) object).date);
        }
        return false;
    }

    public boolean isWeekEnd() {
        return date.getDayOfWeek() == DayOfWeek.FRIDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isStar() {
        return date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfMonth() == CHRISTMAS;
    }

    public boolean isDDayPeriod() {
        return date.getDayOfMonth() <= CHRISTMAS;
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }
}
