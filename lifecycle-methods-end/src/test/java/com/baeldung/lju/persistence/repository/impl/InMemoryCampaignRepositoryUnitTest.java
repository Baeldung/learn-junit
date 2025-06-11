package com.baeldung.lju.persistence.repository.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.lju.domain.model.Campaign;

class InMemoryCampaignRepositoryUnitTest {

    final Logger logger = LoggerFactory.getLogger(InMemoryCampaignRepositoryUnitTest.class);

    InMemoryCampaignRepository campaignRepository;

    @BeforeEach
    void setupDataSource() {
        Campaign existingCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        existingCampaign.setId(1L);
        campaignRepository = new InMemoryCampaignRepository(new HashSet<>(Arrays.asList(existingCampaign)));
        logger.info("@BeforeEach - Initialized Data Source");
        logger.info("Repository reference id: {}", System.identityHashCode(campaignRepository));
        logger.info("Data Source has {} campaigns", campaignRepository.findAll()
          .size());
    }

    @AfterEach
    void cleanup() {
        logger.info("@AfterEach cleanup");
        logger.info("Repository reference id: {}", System.identityHashCode(campaignRepository));
        logger.info("Data Source has {} campaigns", campaignRepository.findAll()
          .size());
    }

    @Test
    void givenExistingCampaign_whenFindById_thenCampaignRetrieved() {
        // when
        Optional<Campaign> retrievedCampaign = campaignRepository.findById(1L);

        // then
        Assertions.assertEquals("C-1-CODE", retrievedCampaign.get()
          .getCode());
    }

    @Test
    void givenExistingCampaign_whenFindByNonExistingId_thenNoCampaignRetrieved() {
        // when
        Optional<Campaign> retrievedCampaign = campaignRepository.findById(99L);

        // then
        Assertions.assertEquals(true, retrievedCampaign.isEmpty());
    }

    // Overriding data source for specific scenario
    @Test
    void givenEmptyDataSource_whenFindAllCampaigns_thenEmptyListRetrieved() {
        // given
        campaignRepository = new InMemoryCampaignRepository(new HashSet<>());

        // when
        List<Campaign> retrievedCampaigns = campaignRepository.findAll();

        // then
        Assertions.assertEquals(true, retrievedCampaigns.isEmpty());
    }

    @Test
    void givenEmptyDataSource_whenSave_thenCampaignIsAssignedId() {
        // when
        Campaign newCampaign = new Campaign("C-NEW-CODE", "New Campaign", "New Campaign Description");
        Campaign savedCampaign = campaignRepository.save(newCampaign);

        // then
        Assertions.assertEquals(true, Objects.nonNull(savedCampaign.getId()));
    }
}