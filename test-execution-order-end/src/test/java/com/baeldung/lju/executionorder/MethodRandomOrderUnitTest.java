package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicInteger;

@TestMethodOrder(MethodOrderer.Random.class)
class MethodRandomOrderUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Test
    void whenRunningExampleOne_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleOne_thenObserveConfiguredOrder");
        Assertions.assertTrue(counter.getAndIncrement() <= 3);
    }

    @Test
    void whenRunningExampleTwo_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleTwo_thenObserveConfiguredOrder");
        Assertions.assertTrue(counter.getAndIncrement() <= 3);
    }

    @Test
    void whenRunningExampleThree_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleThree_thenObserveConfiguredOrder");
        Assertions.assertTrue(counter.getAndIncrement() <= 3);
    }
}
