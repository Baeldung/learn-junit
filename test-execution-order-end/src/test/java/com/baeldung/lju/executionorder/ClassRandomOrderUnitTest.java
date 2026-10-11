package com.baeldung.lju.executionorder;

import org.junit.jupiter.api.*;
import java.util.concurrent.atomic.AtomicInteger;

@TestClassOrder(ClassOrderer.Random.class)
class ClassRandomOrderUnitTest {

    private static final AtomicInteger counter = new AtomicInteger(1);

    @Nested
    class One {
        @Test
        void whenRunningExampleOne_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleOne_thenObserveConfiguredOrder");
            Assertions.assertTrue(counter.getAndIncrement() <= 3);
        }
    }

    @Nested
    class Two {
        @Test
        void whenRunningExampleTwo_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleTwo_thenObserveConfiguredOrder");
            Assertions.assertTrue(counter.getAndIncrement() <= 3);
        }
    }

    @Nested
    class Three {
        @Test
        void whenRunningExampleThree_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleThree_thenObserveConfiguredOrder");
            Assertions.assertTrue(counter.getAndIncrement() <= 3);
        }
    }
}
