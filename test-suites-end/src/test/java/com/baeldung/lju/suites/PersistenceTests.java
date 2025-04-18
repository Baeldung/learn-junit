package com.baeldung.lju.suites;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@DisplayName("Persistence Tests")
@SelectPackages("com.baeldung.lju.persistence")
class PersistenceTests {

}
