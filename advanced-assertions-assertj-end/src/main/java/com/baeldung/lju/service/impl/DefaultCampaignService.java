package com.baeldung.lju.service.impl;

import java.util.List;
import java.util.Optional;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.domain.model.TaskStatus;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.persistence.repository.impl.InMemoryCampaignRepository;
import com.baeldung.lju.service.CampaignService;

public class DefaultCampaignService implements CampaignService {

    private CampaignRepository campaignRepository;

    public DefaultCampaignService() {
        this.campaignRepository = new InMemoryCampaignRepository();
    }

    public DefaultCampaignService(CampaignRepository campaignRepository) {
        super();
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Optional<Campaign> findById(Long id) {
        return campaignRepository.findById(id);
    }

    @Override
    public Campaign create(Campaign campaign) {
        if (campaign.getId() != null) {
            throw new IllegalArgumentException("Can't create Campaign with assigned 'id'");
        }
        return campaignRepository.save(campaign);
    }

    @Override
    public List<Campaign> findCampaigns() {
        return campaignRepository.findAll();
    }

    @Override
    public Optional<Campaign> closeCampaign(Long id) {
        return campaignRepository.findById(id)
            .map(campaign -> {
                campaign.setClosed(true);
                campaign.getTasks()
                    .forEach(task -> task.setStatus(TaskStatus.DONE));
                return campaign;
            });
    }
}
