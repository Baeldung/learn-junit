package com.baeldung.lju;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class HamcrestTest {

    @Test
    void whenTestingAString_thenContainsSubstring() {
        String aString = "This is a string";

        assertThat(aString, containsStringIgnoringCase("STRING"));
        assertThat(aString, startsWith("This is"));
        assertThat(aString, endsWith("a string"));
    }

    @Test
    void whenNumberIsEighteen_thenItsBetweenTenAndTwenty() {
        Long aNumber = 18L;
        assertThat(aNumber, allOf(greaterThan(10L), lessThan(20L)));
    }

    @Test
    void whenDateIsToday_thenItsBetweenYesterdayAndTomorrow() {
        LocalDateTime aDate = LocalDateTime.now();

        assertThat(aDate, allOf(
            greaterThan(LocalDateTime.now().minusDays(1)),
            lessThan(LocalDateTime.now().plusDays(1))
        ));
    }

}
