package com.baeldung.lju;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Index.atIndex;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class AssertJTest {

    @Test
    void whenTestingAString_thenContainsSubstring() {
        String aString = "This is a string";
        assertThat(aString)
            .containsIgnoringCase("STRING")
            .startsWith("This is")
            .endsWith("a string");
    }

    @Test
    void whenDateIsToday_thenItsBetweenYesterdayAndTomorrow() {
        LocalDateTime aDate = LocalDateTime.now();
        assertThat(aDate)
            .isAfter(LocalDateTime.now().minusDays(1))
            .isBefore(LocalDateTime.now().plusDays(1));
    }

    @Test
    void whenTestingAList_thenCheckElementsRegardlessOfTheirOrder() {
        List<Integer> aList = List.of(4, 3, 2, 1);
        assertThat(aList)
            .containsExactly(4, 3, 2, 1)
            .containsExactlyInAnyOrder(1, 2, 3, 4);
    }

    @Test
    void whenTestingAList_thenElementsAreSmallerThanTen_andListFinishesWithOne() {
        List<Integer> aList = List.of(4, 3, 2, 1);
        assertThat(aList).allMatch(nr -> nr < 10)
            .contains(1, atIndex(3));
    }

    @Test
    void whenTestingAMap_thenContainsCorrectKeysAndValues() {
        Map<String, Integer> aMap = Map.of(
            "foo", 2,
            "bar", 4,
            "qux", 6,
            "buzz", 8
        );

        assertThat(aMap)
            .containsEntry("foo", 2)
            .containsEntry("bar", 4)
            .containsKeys("qux", "buzz")
            .doesNotContainKey("fizz")
            .containsValues(2, 4, 6, 8)
            .doesNotContainValue(0);
    }

}
