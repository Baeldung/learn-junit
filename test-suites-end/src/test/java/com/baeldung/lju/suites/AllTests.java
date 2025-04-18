package com.baeldung.lju.suites;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.baeldung.lju")
@ExcludePackages("com.baeldung.lju.suites")
class AllTests {

}
