package com.baeldung.lju.suites;

import org.junit.platform.suite.api.SelectMethod;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectMethod(typeName = "com.baeldung.lju.ApplicationIntegrationTest", name = "mainAppMethodIntegrationTest")
class MainMethodTest {

}
