package com.baeldung.lju.suites;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
//@SelectMethods({
//    @SelectMethod(
//        typeName = "com.baeldung.lju.persistence.repository.impl.InMemoryCampaignRepositoryUnitTest",
//        name = "givenEmptyDataSource_whenFindAllCampaigns_thenEmptyListRertrieved"),
//    @SelectMethod(
//        typeName = "com.baeldung.lju.persistence.repository.impl.InMemoryCampaignRepositoryUnitTest",
//        name = "givenExistingCampaign_whenFindById_thenCampaignRertrieved")
//})
@SelectPackages("com.baeldung.lju")
@ExcludePackages("com.baeldung.lju.suites")
@IncludeTags("fast")
class FastTests {

}
