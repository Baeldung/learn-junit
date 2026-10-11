package com.baeldung.lju;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.baeldung.lju")
@IncludeTags("campaigns | reports")
@ExcludeTags("integration | persistence")
class ReportsSuite {
}
