package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;
import java.util.concurrent.atomic.AtomicInteger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderAnnotationUnitTest {

    // Class-level AtomicInteger
    private static final AtomicInteger counter = new AtomicInteger(1);

    @Test
    @Order(3)
    void whenRunningExampleOne_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleOne_thenObserveConfiguredOrder, order 3");
        Assertions.assertEquals(3, counter.getAndIncrement());
    }

    @Test
    @Order(1)
    void whenRunningExampleTwo_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleTwo_thenObserveConfiguredOrder, order 1");
        Assertions.assertEquals(1, counter.getAndIncrement());
    }

    @Test
    @Order(2)
    void whenRunningExampleThree_thenObserveConfiguredOrder() {
        System.out.println("Running whenRunningExampleThree_thenObserveConfiguredOrder, order 2");
        Assertions.assertEquals(2, counter.getAndIncrement());
    }
}
