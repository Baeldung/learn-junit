package com.baeldung.lju.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import com.baeldung.lju.domain.model.Campaign;

class InMemoryCampaignRepositoryRepeatedUnitTest {
    @RepeatedTest(5)
    void whenSavingCampaignRepeatedly_thenShouldAssignIds() {
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository();

        Campaign campaign = new Campaign("TEST-CODE", "Test Campaign", "Repeated test scenario");
        Campaign savedCampaign = repository.save(campaign);

        assertNotNull(savedCampaign.getId(),
                "Campaign ID should be assigned in repeated test");
    }

    @RepeatedTest(value = 5, name = "Test run {currentRepetition}/{totalRepetitions}")
    void whenSavingCampaignRepeatedly_thenShouldAssignIdsWithCustomNaming() {
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository();

        Campaign campaign = new Campaign("TEST-CODE", "Test Campaign", "Repeated test scenario");
        Campaign savedCampaign = repository.save(campaign);

        assertNotNull(savedCampaign.getId(),
                "Campaign ID should be assigned in repeated test");
    }

    @RepeatedTest(value = 5, name = "Test run {currentRepetition}/{totalRepetitions}")
    void whenSavingCampaignRepeatedly_thenShouldAssignIdsWithRepetitionInfo(RepetitionInfo repetitionInfo) {
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository();

        System.out.println("Running repetition " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());

        Campaign campaign = new Campaign("TEST-CODE", "Test Campaign", "Repeated test scenario");
        Campaign savedCampaign = repository.save(campaign);

        assertNotNull(savedCampaign.getId(),
                "Campaign ID should be assigned in repeated test");
    }

    /*
    // Uncomment this test to see the failure threshold behaviour
    @RepeatedTest(value = 10, failureThreshold = 2)
    void whenSavingCampaignRepeatedly_thenShouldAssignIdsWithFailureThreshold(RepetitionInfo repetitionInfo) {
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository();

        Campaign campaign = new Campaign("TEST-CODE", "Test Campaign", "Repeated test scenario");
        Campaign savedCampaign = repository.save(campaign);

        if (repetitionInfo.getCurrentRepetition() % 2 == 0) {
            fail("This repetition is a flaky one!");
        } else {
            assertNotNull(savedCampaign.getId(),
                    "Campaign ID should be assigned in repeated test");
        }
    }
    */
}