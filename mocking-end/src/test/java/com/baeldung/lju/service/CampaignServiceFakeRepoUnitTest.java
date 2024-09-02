package com.baeldung.lju.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.persistence.repository.impl.FakeCampaignRepository;
import com.baeldung.lju.service.impl.DefaultCampaignService;

class CampaignServiceFakeRepoUnitTest {

    @Test
    void whenClosingACampaign_thenReturnCorrectData_usingFake() {
        // given
        CampaignRepository repository = new FakeCampaignRepository(new Campaign("test-code", "test-name", "test-description"));
        CampaignService service = new DefaultCampaignService(repository);

        // when
        Optional<Campaign> result = service.closeCampaign(1L);

        // then
        assertTrue(result.isPresent());
        Campaign campaign = result.get();

        assertEquals("test-code", campaign.getCode());
        assertEquals("test-name", campaign.getName());
        assertEquals("test-description", campaign.getDescription());

        // and
        assertTrue(campaign.isClosed());
        campaign.getTasks()
            .forEach(task -> assertEquals(TaskStatus.DONE, task.getStatus()));
    }

    @Test
    void whenClosingACampaignWhichIsNotFound_thenReturnEmpty_usingFake() {
        // given
        CampaignRepository repository = new FakeCampaignRepository(null);
        CampaignService service = new DefaultCampaignService(repository);

        // when
        Optional<Campaign> result = service.closeCampaign(1L);

        // then
        assertTrue(result.isEmpty());
    }

}
