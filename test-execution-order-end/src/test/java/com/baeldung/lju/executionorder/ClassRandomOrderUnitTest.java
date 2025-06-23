package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;
import java.util.concurrent.atomic.AtomicInteger;

@TestClassOrder(ClassOrderer.Random.class)
class ClassRandomOrderUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Nested
    class One {
        @Test
        void testOne() {
            System.out.println("Running testOne");
            Assertions.assertTrue(counter.getAndIncrement() <= 3);
        }
    }

    @Nested
    class Two {
        @Test
        void testTwo() {
            System.out.println("Running testTwo");
            Assertions.assertTrue(counter.getAndIncrement() <= 3);
        }
    }

    @Nested
    class Three {
        @Test
        void testThree() {
            System.out.println("Running testThree");
            Assertions.assertTrue(counter.getAndIncrement() <= 3);
        }
    }
}
