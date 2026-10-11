package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicInteger;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class MethodDisplayNameOrderUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Test
    @DisplayName("1. Test One")
    void whenRunningExampleOne_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleOne_thenObserveConfiguredOrder");
        Assertions.assertEquals(1, counter.getAndIncrement());
    }

    @Test
    @DisplayName("2. Test Two")
    void whenRunningExampleTwo_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleTwo_thenObserveConfiguredOrder");
        Assertions.assertEquals(2, counter.getAndIncrement());
    }

    @Test
    @DisplayName("3. Test Three")
    void whenRunningExampleThree_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleThree_thenObserveConfiguredOrder");
        Assertions.assertEquals(3, counter.getAndIncrement());
    }
}
