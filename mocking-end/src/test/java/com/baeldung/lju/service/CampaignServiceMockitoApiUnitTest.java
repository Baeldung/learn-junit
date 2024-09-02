package com.baeldung.lju.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.service.impl.DefaultCampaignService;

class CampaignServiceMockitoApiUnitTest {

    @Test
    void whenClosingACampaign_thenReturnCorrectData() {
        // given
        CampaignRepository repository = Mockito.mock(CampaignRepository.class);
        CampaignService service = new DefaultCampaignService(repository);

        Campaign testCampaign = new Campaign("test-code", "test-name", "test-description");
        Mockito.when(repository.findById(1L))
            .thenReturn(Optional.of(testCampaign));

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
    void whenClosingACampaignWhichIsNotFound_thenReturnEmpty() {
        // given
        CampaignRepository repository = Mockito.mock(CampaignRepository.class);
        CampaignService service = new DefaultCampaignService(repository);

        Mockito.when(repository.findById(1L))
            .thenReturn(Optional.empty());

        // when
        Optional<Campaign> result = service.closeCampaign(1L);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void whenClosingCampaigns_thenClosesCampaignIfFound() {
        // given
        CampaignRepository repository = Mockito.mock(CampaignRepository.class);
        CampaignService service = new DefaultCampaignService(repository);

        Campaign testCampaign = new Campaign("test-code", "test-name", "test-description");
        Mockito.when(repository.findById(Mockito.anyLong())) .thenReturn(Optional.of(testCampaign));

        // when
        Optional<Campaign> result = service.closeCampaign(99L);

        // then
        assertTrue(result.isPresent());
        Campaign campaign = result.get();

        assertEquals("test-code", campaign.getCode());
    }

}
