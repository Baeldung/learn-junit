package com.baeldung.lju.persistence.repository.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;

public class FakeCampaignRepository implements CampaignRepository {

    private Campaign mockedCampaign;

    public FakeCampaignRepository(Campaign mockedCampaign) {
        this.mockedCampaign = mockedCampaign;
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return Optional.ofNullable(mockedCampaign);
    }

    @Override
    public Campaign save(Campaign campaign) {
        return mockedCampaign;
    }

    @Override
    public List<Campaign> findAll() {
        if (mockedCampaign != null) {
            return Collections.singletonList(mockedCampaign);
        }
        return Collections.emptyList();
    }
}
