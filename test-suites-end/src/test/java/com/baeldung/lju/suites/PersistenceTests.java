package com.baeldung.lju.suites;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SuiteDisplayName("Persistence Tests")
@SelectPackages("com.baeldung.lju.persistence")
class PersistenceTests {

}
