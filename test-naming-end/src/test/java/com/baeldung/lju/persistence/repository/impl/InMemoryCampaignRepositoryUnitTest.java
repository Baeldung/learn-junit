package com.baeldung.lju.persistence.repository.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import com.baeldung.lju.CustomDisplayNameGenerator;
import com.baeldung.lju.domain.model.Campaign;

@DisplayName("In-memory Campaign repository unit test")
// @DisplayNameGeneration(DisplayNameGenerator.Standard.class)
// @DisplayNameGeneration(DisplayNameGenerator.Simple.class)
// @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
// @DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
// @IndicativeSentencesGeneration(generator = DisplayNameGenerator.ReplaceUnderscores.class, separator = " - ")
@DisplayNameGeneration(CustomDisplayNameGenerator.class)
class InMemoryCampaignRepositoryUnitTest {

    @Test
    @DisplayName("No data scenario: Given an empty data source, " + "when finding all campaigns, then an empty list is retrieved")
    void givenEmptyDataSource_whenFindAllCampaigns_thenEmptyListRetrieved() {
        // given 
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>());

        // when
        List<Campaign> retrievedCampaigns = campaignRepository.findAll();

        // then
        Assertions.assertEquals(true, retrievedCampaigns.isEmpty());
    }

    @Test
    void givenExistingCampaign_whenFindById_thenCampaignRetrieved() {
        // given 
        Campaign existingCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        existingCampaign.setId(1L);
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>(Arrays.asList(existingCampaign)));

        // when
        Optional<Campaign> retrievedCampaign = campaignRepository.findById(1L);

        // then
        Assertions.assertEquals(existingCampaign, retrievedCampaign.get());
    }

    @Test
    void givenExistingCampaign_whenFindByNonExistingId_thenNoCampaignRetrieved() {
        // given 
        Campaign existingCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        existingCampaign.setId(1L);
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>(Arrays.asList(existingCampaign)));

        // when
        Optional<Campaign> retrievedCampaign = campaignRepository.findById(99L);

        // then
        Assertions.assertEquals(true, retrievedCampaign.isEmpty());
    }

    @Test
    void givenEmptyDataSource_whenSave_thenCampaignIsAssignedId() {
        // given 
        InMemoryCampaignRepository campaignRepository = new InMemoryCampaignRepository(new HashSet<>());

        // when
        Campaign newCampaign = new Campaign("C-NEW-CODE", "New Campaign", "New Campaign Description");
        Campaign savedCampaign = campaignRepository.save(newCampaign);

        // then
        Assertions.assertEquals(true, Objects.nonNull(savedCampaign.getId()));
    }

}
