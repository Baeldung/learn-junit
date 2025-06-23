package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;
import java.util.concurrent.atomic.AtomicInteger;

@TestClassOrder(ClassOrderer.DisplayName.class)
class ClassDisplayNameOrderUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Nested
    @DisplayName("1. Class One")
    class One {
        @Test
        void testOne() {
            System.out.println("Running testOne");
            Assertions.assertEquals(1, counter.getAndIncrement());
        }
    }

    @Nested
    @DisplayName("2. Class Two")
    class Two {
        @Test
        void testTwo() {
            System.out.println("Running testTwo");
            Assertions.assertEquals(2, counter.getAndIncrement());
        }
    }

    @Nested
    @DisplayName("3. Class Three")
    class Three {
        @Test
        void testThree() {
            System.out.println("Running testThree");
            Assertions.assertEquals(3, counter.getAndIncrement());
        }
    }
}
