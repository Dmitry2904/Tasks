package by.dmitry.shelepen.task3.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

class DateTimeUtilTest {

    @ParameterizedTest
    @MethodSource("incorrectTimeZones")
    void testCheckAndCorrectTimeZone(String inputDate, String expected) {
        String actual = new DateTimeUtil().checkAndCorrectTimeZone(inputDate);
        Assertions.assertEquals(expected, actual);
    }


    private static Stream<Arguments> incorrectTimeZones() {
        return Stream.of(
                arguments("2029-10-22T15:24:01-2", "2029-10-22T15:24:01-02"),
                arguments("2029-10-22T15:24:01-12", "2029-10-22T15:24:01-12"),
                arguments("2029-10-22T15:24:01+2", "2029-10-22T15:24:01+02"),
                arguments("2029-10-22T15:24:01+12", "2029-10-22T15:24:01+12")
        );
    }
}