package com.baeldung.lju.persistence.repository.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.baeldung.lju.domain.model.Campaign;

class InMemoryCampaignRepositoryUnitTest {

    @Test
    void givenEmptyDataSource_whenFindAllCampaigns_thenEmptyListRetrieved() {
        // given
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(new HashSet<>());

        // when
        List<Campaign> campaigns = repository.findAll();

        // then
        Assertions.assertTrue(campaigns.isEmpty());
    }

    @Test
    void givenExistingCampaign_whenFindById_thenCampaignRetrieved() {
        // given
        Campaign campaign = new Campaign("P-1-CODE", "Campaign 1", "Campaign 1 Description");
        campaign.setId(1L);
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(Set.of(campaign));

        // when
        Optional<Campaign> retrievedCampaign = repository.findById(1L);

        // then
        Assertions.assertTrue(retrievedCampaign.isPresent());
        Assertions.assertEquals(campaign, retrievedCampaign.get());
    }

    @Test
    void givenExistingCampaign_whenFindByNonExistingId_thenNoCampaignRetrieved() {
        // given
        Campaign campaign = new Campaign("P-1-CODE", "Campaign 1", "Campaign 1 Description");
        campaign.setId(1L);
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(Set.of(campaign));

        // when
        Optional<Campaign> retrievedCampaign = repository.findById(99L);

        // then
        Assertions.assertTrue(retrievedCampaign.isEmpty());
    }

    @Test
    void givenEmptyDataSource_whenSave_thenCampaignIsAssignedId() {
        // given
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(new HashSet<>());

        // when
        Campaign campaign= new Campaign("P-NEW-CODE", "New Campaign", "New Campaign Description");
        repository.save(campaign);

        // then
        Assertions.assertTrue(campaign.getId() != null);
    }
}
