package com.baeldung.lju;

import com.baeldung.lju.junit.IntegrationTest;

class ApplicationIntegrationTest {

    @IntegrationTest
    void mainAppMethodIntegrationTest() {
        LjuApp.main(new String[] {});
    }

}