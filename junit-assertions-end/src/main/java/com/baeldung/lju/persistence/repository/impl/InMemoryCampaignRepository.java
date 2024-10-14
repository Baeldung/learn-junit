package com.baeldung.lju.persistence.repository.impl;

import static java.util.List.copyOf;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;

public class InMemoryCampaignRepository implements CampaignRepository {

    private Set<Campaign> campaigns;

    public InMemoryCampaignRepository() {
        super();
        this.campaigns = new HashSet<>();
    }

    public InMemoryCampaignRepository(Set<Campaign> campaigns) {
        super();
        this.campaigns = campaigns;
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return campaigns.stream()
            .filter(p -> p.getId()
                .equals(id))
            .findFirst();
    }

    @Override
    public Campaign save(Campaign campaign) {
        Long campaignId = campaign.getId();
        if (campaignId == null) {
            campaign.setId(new Random().nextLong(Long.MAX_VALUE));
        } else {
            findById(campaignId).ifPresent(campaigns::remove);
        }
        campaigns.add(campaign);
        return campaign;
    }

    @Override
    public List<Campaign> findAll() {
        return copyOf(campaigns);
    }
}
