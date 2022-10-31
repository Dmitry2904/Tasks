package by.dmitry.shelepen.task1.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class StringParserUtilTest {


    @ParameterizedTest
    @MethodSource("testStringsForParsing")
    void parseValueBetweenPrefixAndSuffix(String inputString, String prefix, String suffix, String expected) {
        String stringAfterParsing = new StringParserUtil().parseValueBetweenPrefixAndSuffix(inputString, prefix, suffix);
        Assertions.assertEquals(expected, stringAfterParsing);
    }


    private static Stream<Arguments> testStringsForParsing() {
        String testInputString1 = "<!DOCTYPE html><html lang=\"en\">" +
                "<head><meta charset=\"UTF-8\"><title>Title</title></head><body></body></html>";

        String expectedString1 = "<head><meta charset=\"UTF-8\"><title>Title</title></head>";

        String testInputString2 = "<!DOCTYPE html><html lang=\"en\">" +
                "<head><meta charset=\"UTF-8\"><title>Title</title></head><body><h1>Welcome</h1></body></html>";

        String expectedString2 = "<body><h1>Welcome</h1></body>";

        return Stream.of(
                arguments(testInputString1, "<head>", "</head>", expectedString1),
                arguments(testInputString2, "<body>", "</body>", expectedString2)
        );
    }
}