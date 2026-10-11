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
        void whenRunningExampleOne_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleOne_thenObserveConfiguredOrder");
            Assertions.assertEquals(1, counter.getAndIncrement());
        }
    }

    @Nested
    @DisplayName("2. Class Two")
    class Two {
        @Test
        void whenRunningExampleTwo_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleTwo_thenObserveConfiguredOrder");
            Assertions.assertEquals(2, counter.getAndIncrement());
        }
    }

    @Nested
    @DisplayName("3. Class Three")
    class Three {
        @Test
        void whenRunningExampleThree_thenObserveConfiguredOrder() {
            System.out.println("Running whenRunningExampleThree_thenObserveConfiguredOrder");
            Assertions.assertEquals(3, counter.getAndIncrement());
        }
    }
}
