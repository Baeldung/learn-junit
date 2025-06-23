package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;
import java.util.concurrent.atomic.AtomicInteger;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class ClassOrderAnnotationUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Nested
    @Order(3)
    class One {
        @Test
        void testOne() {
            System.out.println("Running testOne, order 3");
            Assertions.assertEquals(3, counter.getAndIncrement());
        }
    }

    @Nested
    @Order(1)
    class Two {
        @Test
        void testTwo() {
            System.out.println("Running testTwo, order 1");
            Assertions.assertEquals(1, counter.getAndIncrement());
        }
    }

    @Nested
    @Order(2)
    class Three {
        @Test
        void testThree() {
            System.out.println("Running testThree, order 2");
            Assertions.assertEquals(2, counter.getAndIncrement());
        }
    }
}
