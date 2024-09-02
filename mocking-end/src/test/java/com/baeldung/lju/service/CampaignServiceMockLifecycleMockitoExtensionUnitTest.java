package com.baeldung.lju.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.service.impl.DefaultCampaignService;

@ExtendWith(MockitoExtension.class)
class CampaignServiceMockLifecycleMockitoExtensionUnitTest {

    @Mock
    private CampaignRepository repository;

    @InjectMocks
    private DefaultCampaignService service;

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
