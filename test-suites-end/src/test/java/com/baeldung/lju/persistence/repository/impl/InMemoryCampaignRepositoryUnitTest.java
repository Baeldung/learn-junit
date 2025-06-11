package com.baeldung.lju.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.baeldung.lju.domain.model.Campaign;

class InMemoryCampaignRepositoryUnitTest {

    @Test
    @Tag("fast")
    void givenEmptyDataSource_whenFindAllCampaigns_thenEmptyListRertrieved() {
        // given 
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>());

        // when
        List<Campaign> retrievedCampaigns = campaignRepository.findAll();

        // then
        assertEquals(true, retrievedCampaigns.isEmpty());
    }

    @Test
    @Tag("fast")
    void givenExistingCampaign_whenFindById_thenCampaignRertrieved() {
        // given 
        Campaign existingCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        existingCampaign.setId(1L);
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>(Arrays.asList(existingCampaign)));

        // when
        Optional<Campaign> retrievedCampaign = campaignRepository.findById(1L);

        // then
        assertEquals(existingCampaign, retrievedCampaign.get());
    }

    @Test
    void givenExistingCampaign_whenFindByNonExistingId_thenNoCampaignRertrieved() {
        // given 
        Campaign existingCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        existingCampaign.setId(1L);
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>(Arrays.asList(existingCampaign)));

        // when
        Optional<Campaign> retrievedCampaign = campaignRepository.findById(99L);

        // then
        assertEquals(true, retrievedCampaign.isEmpty());
    }

    @Test
    void givenEmptyDataSource_whenSave_thenCampaignIsAssignedId() {
        // given 
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>());

        // when
        Campaign newCampaign = new Campaign("C-NEW-CODE", "New Campaign", "New Campaign Description");
        Campaign savedCampaign = campaignRepository.save(newCampaign);

        // then
        assertEquals(true, Objects.nonNull(savedCampaign.getId()));
    }

}
