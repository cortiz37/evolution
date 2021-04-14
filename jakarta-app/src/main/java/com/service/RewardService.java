package com.service;

import com.model.Reward;
import com.repository.RewardRepository;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Dependent
public class RewardService {

    @Inject
    private RewardRepository rewardRepository;

    public List<Reward> getAll() {
        return rewardRepository.getDatabase();
    }

    public Optional<List<Reward>> getAllByCustomerId(String customerId) {
        return rewardRepository.getAllByCustomerId(customerId);
    }

    public List<Reward> getAllActiveByCustomerId(String customerId) {
        return rewardRepository.getAllByCustomerId(customerId)
            .map(l -> l.stream()
                .filter(Reward::isActive)
                .sorted(Comparator.comparing(Reward::getDate))
                .collect(Collectors.toList())
            ).orElse(Collections.emptyList());
    }

    public Reward create(Reward reward, String customerId) {
        reward.setId(UUID.randomUUID().toString());
        return rewardRepository.save(reward, customerId);
    }

    public boolean delete(String id) {
        return rewardRepository.delete(id);
    }

    public boolean registerAmount(String customerId, double total) {
        return rewardRepository.registerAmount(customerId, total);
    }
}