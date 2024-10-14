package com.baeldung.lju;

import com.baeldung.lju.domain.model.Campaign;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssertionMessageTest {

    @Test
    void whenCreatingCode_thenSetCorrectCodeAndTasks() {
        Campaign campaign = new Campaign("code", "name", "description");

        assertEquals("code", campaign.getCode());
        assertTrue(campaign.getTasks().isEmpty());
    }

    @Test
    void whenCreatingCode_thenSetCorrectCodeAndTasks_andAddAssertionMessage() {
        Campaign campaign = new Campaign("code", "name", "description");

        assertNull(campaign.getId(), "a new campaign should have a null ID");
        assertNotNull(campaign, "campaign should not be null");
        assertNotEquals(campaign.getCode(), campaign.getName(), "name should not be equal to code");
        assertInstanceOf(Campaign.class, campaign, "campaign should be an instance of Campaign");
    }

}
