package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicInteger;

@TestMethodOrder(MethodOrderer.Random.class)
class MethodRandomOrderUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Test
    void testOne() {
        System.out.println("Running testOne");
        Assertions.assertTrue(counter.getAndIncrement() <= 3);
    }

    @Test
    void testTwo() {
        System.out.println("Running testTwo");
        Assertions.assertTrue(counter.getAndIncrement() <= 3);
    }

    @Test
    void testThree() {
        System.out.println("Running testThree");
        Assertions.assertTrue(counter.getAndIncrement() <= 3);
    }
}
