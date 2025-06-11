package com.baeldung.lju.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import com.baeldung.lju.EnabledOnWeekends;
import com.baeldung.lju.domain.model.Campaign;

class InMemoryCampaignRepositoryUnitTest {

    @Test
    @EnabledIfEnvironmentVariable(named = "MY_ENV_VARIABLE", matches = "test")
    void givenEmptyDataSource_whenFindAllCampaigns_thenEmptyListRetrieved() {
        // given 
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>());

        // when
        List<Campaign> retrievedCampaigns = campaignRepository.findAll();

        // then
        assertEquals(true, retrievedCampaigns.isEmpty());
    }

    @Test
    @DisabledIfSystemProperty(named = "my.system.property", matches = "test")
    void givenExistingCampaign_whenFindById_thenCampaignRetrieved() {
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
    @EnabledIf("com.baeldung.lju.IsWeekendUtility#isWeekend")
    void givenExistingCampaign_whenFindByNonExistingId_thenNoCampaignRetrieved() {
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
    @EnabledOnWeekends
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