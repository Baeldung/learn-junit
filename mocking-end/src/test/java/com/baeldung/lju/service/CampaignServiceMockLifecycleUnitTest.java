package com.baeldung.lju.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.service.impl.DefaultCampaignService;

class CampaignServiceMockLifecycleUnitTest {

    @Mock
    private CampaignRepository repository;

    @InjectMocks
    private DefaultCampaignService service;

    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenClosingACampaign_thenReturnCorrectData() {
        // given
        Campaign testCampaign = new Campaign("test-code", "test-name", "test-description");
        Mockito.when(repository.findById(1L))
            .thenReturn(Optional.of(testCampaign));

        // when/then
        assertTrue(service.closeCampaign(1L)
            .isPresent());
    }

}
