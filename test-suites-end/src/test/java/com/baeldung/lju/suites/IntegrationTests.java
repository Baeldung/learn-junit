package com.baeldung.lju.suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(names = { "com.baeldung.lju.ApplicationIntegrationTest" })
class IntegrationTests {

}
