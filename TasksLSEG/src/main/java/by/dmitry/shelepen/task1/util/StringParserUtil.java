package by.dmitry.shelepen.task1.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParserUtil {
    public String parseValueBetweenPrefixAndSuffix(String allHtmlDataInString, String prefix, String suffix) {
        String regex = String.format("%s(.+?)%s", prefix, suffix);
        final Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        final Matcher matcher = pattern.matcher(allHtmlDataInString);
        String tagTable = "";
        if (matcher.find()) {
            tagTable = prefix + matcher.group(1) + suffix;
        }
        return tagTable;
    }


}
