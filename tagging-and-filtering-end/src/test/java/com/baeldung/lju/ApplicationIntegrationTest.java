package com.baeldung.lju;

import com.baeldung.lju.junit.IntegrationTest;

class ApplicationIntegrationTest {

    @IntegrationTest
    void whenRunningApplication_thenCompleteWithoutException() {
        LjuApp.main(new String[] {});
    }

}