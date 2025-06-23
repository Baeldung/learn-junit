package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicInteger;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class MethodDisplayNameOrderUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Test
    @DisplayName("1. Test One")
    void testOne() {
        System.out.println("Running testOne");
        Assertions.assertEquals(1, counter.getAndIncrement());
    }

    @Test
    @DisplayName("2. Test Two")
    void testTwo() {
        System.out.println("Running testTwo");
        Assertions.assertEquals(2, counter.getAndIncrement());
    }

    @Test
    @DisplayName("3. Test Three")
    void testThree() {
        System.out.println("Running testThree");
        Assertions.assertEquals(3, counter.getAndIncrement());
    }
}
