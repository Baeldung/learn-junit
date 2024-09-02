package com.baeldung.lju.persistence.repository.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baeldung.lju.LjuApp;
import com.baeldung.lju.domain.model.Campaign;

public class InMemoryCampaignRepositoryWithStaticResourceUnitTest {

    static Logger logger = LoggerFactory.getLogger(LjuApp.class);

    static InMemoryCampaignRepository staticCampaignRepository;

    @BeforeAll
    public static void setupStaticDataSource() {
        Campaign existingCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        existingCampaign.setId(1L);
        staticCampaignRepository = new InMemoryCampaignRepository(new HashSet<>(Arrays.asList(existingCampaign)));
        logger.info("STATIC @BeforeAll STATIC Initialized Data Source");
        logger.info("Repository reference id: {}", System.identityHashCode(staticCampaignRepository));
        logger.info("Data Source has {} campaigns", staticCampaignRepository.findAll()
          .size());
    }

    @AfterAll
    public static void staticCleanup() {
        logger.info("STATIC @AfterAll cleanup");
        logger.info("Repository reference id: {}", System.identityHashCode(staticCampaignRepository));
        logger.info("Data Source has {} campaigns", staticCampaignRepository.findAll()
          .size());
    }

    @Test
    public void givenStaticDatasource_whenFindById_thenCampaignRetrieved() {
        // when
        Optional<Campaign> retrievedCampaign = staticCampaignRepository.findById(1L);

        // then
        Assertions.assertEquals("C-1-CODE", retrievedCampaign.get()
          .getCode());
    }

    @Test
    public void givenStaticDatasource_whenFindByNonExistingId_thenNoCampaignRetrieved() {
        // when
        Optional<Campaign> retrievedCampaign = staticCampaignRepository.findById(99L);

        // then
        Assertions.assertEquals(true, retrievedCampaign.isEmpty());
    }

    @Test
    public void givenStaticDatasource_whenSave_thenCampaignIsAssignedId() {
        // when
        Campaign newCampaign = new Campaign("C-NEW-CODE", "New Campaign", "New Campaign Description");
        Campaign savedCampaign = staticCampaignRepository.save(newCampaign);

        // then
        Assertions.assertEquals(true, Objects.nonNull(savedCampaign.getId()));
    }
}
