package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;
import java.util.concurrent.atomic.AtomicInteger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MethodOrderAnnotationUnitTest {

    // Class-level AtomicInteger
    private static final AtomicInteger counter = new AtomicInteger(1);

    @Test
    @Order(3)
    void testOne() {
        System.out.println("Running testOne, order 3");
        Assertions.assertEquals(3, counter.getAndIncrement());
    }

    @Test
    @Order(1)
    void testTwo() {
        System.out.println("Running testTwo, order 1");
        Assertions.assertEquals(1, counter.getAndIncrement());
    }

    @Test
    @Order(2)
    void testThree() {
        System.out.println("Running testThree, order 2");
        Assertions.assertEquals(2, counter.getAndIncrement());
    }
}
