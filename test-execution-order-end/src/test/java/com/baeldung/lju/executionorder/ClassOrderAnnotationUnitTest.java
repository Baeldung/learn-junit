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
        void whenRunningExampleOne_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleOne_thenObserveConfiguredOrder, order 3");
            Assertions.assertEquals(3, counter.getAndIncrement());
        }
    }

    @Nested
    @Order(1)
    class Two {
        @Test
        void whenRunningExampleTwo_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleTwo_thenObserveConfiguredOrder, order 1");
            Assertions.assertEquals(1, counter.getAndIncrement());
        }
    }

    @Nested
    @Order(2)
    class Three {
        @Test
        void whenRunningExampleThree_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleThree_thenObserveConfiguredOrder, order 2");
            Assertions.assertEquals(2, counter.getAndIncrement());
        }
    }
}
