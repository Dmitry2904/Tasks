package by.dmitry.shelepen.task3.util;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DateTimeUtil {

    public static final String REGEX_TIME_ZONE = "[+-]\\d{1,2}$";


    public String checkAndCorrectTimeZone(String date) {
        String timeZone = parseTimeZoneFromString(date);
        if (timeZone.length() == 2) {
            date = changeIncorrectTimeZoneInDate(date, timeZone);
        }
        return date;
    }

    /**
     * This method get incorrect timeZone (example: -2, +3 etc.)
     * and insert 0 before digit (example correct timeZone: -02, +03 etc.).
     * Then in date replace incorrect timeZone with correct timeZone.
     * <p>
     * Example 1:
     * input: "2029-10-22T15:24:01-2"
     * output: "2029-10-22T15:24:01-02"
     * <p>
     * Example 2:
     * input: "2029-10-22T15:24:01+3"
     * output: "2029-10-22T15:24:01+03"
     *
     * @param date     - input String value of a date.
     * @param timeZone - last 2 symbols that means timeZone. Example -2, +3 etc.
     * @return String date with a correct time zone.
     */
    private String changeIncorrectTimeZoneInDate(String date, String timeZone) {
        StringBuilder builder = new StringBuilder(timeZone);
        setZeroBeforeSingleDigitInTimeZone(builder);
        String dateWithoutTimeZone = date.substring(0, date.length() - 2);
        setDateToBegin(dateWithoutTimeZone, builder);
        return builder.toString();
    }

    private void setZeroBeforeSingleDigitInTimeZone(StringBuilder builder) {
        builder.insert(1, '0');
    }

    private void setDateToBegin(String dateWithoutTimeZone, StringBuilder builder) {
        builder.insert(0, dateWithoutTimeZone);
    }


    private String parseTimeZoneFromString(String text) {
        Pattern pattern = Pattern.compile(REGEX_TIME_ZONE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return "";
    }
}
