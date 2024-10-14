package com.baeldung.lju.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.baeldung.lju.domain.model.Campaign;

public interface CampaignRepository {

    Optional<Campaign> findById(Long id);

    Campaign save(Campaign campaign);

    List<Campaign> findAll();
}
