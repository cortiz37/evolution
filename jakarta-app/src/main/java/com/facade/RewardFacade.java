package com.facade;

import com.model.Reward;
import com.service.RewardService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class RewardFacade {

    @Inject
    private RewardService rewardService;

    public List<Reward> getAll() {
        return rewardService.getAll();
    }

    public Optional<List<Reward>> getAllByCustomerId(String customerId) {
        return rewardService.getAllByCustomerId(customerId);
    }

    public List<Reward> getAllActiveByCustomerId(String customerId) {
        return rewardService.getAllActiveByCustomerId(customerId);
    }

    public Reward create(Reward reward, String customerId) {
        return rewardService.create(reward, customerId);
    }

    public boolean delete(String id) {
        return rewardService.delete(id);
    }
}
