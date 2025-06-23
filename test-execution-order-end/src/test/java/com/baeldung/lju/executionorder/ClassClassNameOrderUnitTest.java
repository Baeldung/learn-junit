package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;
import java.util.concurrent.atomic.AtomicInteger;

@TestClassOrder(ClassOrderer.ClassName.class)
class ClassClassNameOrderUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Nested
    class One {
        @Test
        void testOne() {
            System.out.println("Running testOne");
            Assertions.assertEquals(1, counter.getAndIncrement());
        }
    }

    @Nested
    class Two {
        @Test
        void testTwo() {
            System.out.println("Running testTwo");
            Assertions.assertEquals(3, counter.getAndIncrement());
        }
    }

    @Nested
    class Three {
        @Test
        void testThree() {
            System.out.println("Running testThree");
            Assertions.assertEquals(2, counter.getAndIncrement());
        }
    }
}
