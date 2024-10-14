package com.baeldung.lju.persistence.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.service.CampaignService;
import com.baeldung.lju.service.impl.DefaultCampaignService;

@ExtendWith(MockitoExtension.class)
public class DefaultCampaignServiceUnitTest {

    @Mock
    CampaignRepository campaignRepository;

    CampaignService campaignService;

    @BeforeEach
    public void setupDataSource() {
        campaignService = new DefaultCampaignService(campaignRepository);
    }

    @Test
    public void givenMockedPersistedCampaign_whenFindById_thenCodeEqualsAndNameNotEquals() {
        //given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        when(campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // when
        Campaign campaign = campaignService.findById(3L)
            .get();

        // then
        Campaign expectedCampaign = new Campaign("C-1-CODE", "Different Name", "Different Description");
        expectedCampaign.setId(99L);

        assertEquals(expectedCampaign.getCode(), campaign.getCode(), "The code should be equal");
        assertNotEquals(expectedCampaign.getName(), campaign.getName());
        
        // if we change the expectedCampaign code, the assertion will fail
        //expectedCampaign = new Campaign("C-2-CODE", "Different Name", "Different Description");
        //assertEquals(expectedCampaign.getCode(), campaign.getCode(), "The code should be equal");

    }

    @Test
    public void givenMockedPersistedCampaign_whenFindById_thenCampaignIsTheSameAsMockedAndDifferentFromExpected() {
        //given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        when(campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // when
        Campaign campaign = campaignService.findById(3L)
            .get();

        // then
        Campaign expectedCampaign = new Campaign("C-1-CODE", "Different Name", "Different Description");
        expectedCampaign.setId(99L);

        assertSame(mockedCampaign, campaign);
        assertNotSame(expectedCampaign, campaign);
    }

    @Test
    public void givenMockedPersistedCampaigns_whenFindAll_thenCampaignsEquals() {
        //given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        when(campaignRepository.findAll()).thenReturn(List.of(mockedCampaign));

        // when
        List<Campaign> campaigns = campaignService.findCampaigns();

        // then
        assertIterableEquals(List.of(mockedCampaign), campaigns);
    }

    @Test
    public void givenMockedPersistedCampaign_whenFindById_thenDescriptionIsBlank() {
        //given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "");
        when(campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // when
        Campaign campaign = campaignService.findById(3L)
            .get();

        // then
        assertTrue(campaign.getDescription()
            .isBlank());
    }

    @Test
    public void givenMockedPersistedCampaign_whenFindById_thenIsClosedFalse() {
        //given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        when(campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // when
        Campaign campaign = campaignService.findById(3L)
            .get();

        // then
        assertFalse(campaign.isClosed());
    }

    @Test
    public void givenMockedPersistedCampaign_whenFindById_thenIdNull() {
        //given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        when(campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // when
        Campaign campaign = campaignService.findById(3L)
            .get();

        // then
        assertNull(campaign.getId());
    }

    @Test
    public void givenMockedPersistedCampaign_whenFindById_thenCodeNotNull() {
        //given
        Campaign mockedCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        when(campaignRepository.findById(3L)).thenReturn(Optional.of(mockedCampaign));

        // when
        Campaign campaign = campaignService.findById(3L)
            .get();

        // then
        assertNotNull(campaign.getCode());
    }

}