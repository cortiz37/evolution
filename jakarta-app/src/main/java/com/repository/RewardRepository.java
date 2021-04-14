package com.repository;

import com.model.Reward;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * In memory 'database'
 */
@ApplicationScoped
public class RewardRepository {

    private final static double AUTO_REWARD_LIMIT = 1000;
    private final static double AUTO_REWARD_DISCOUNT = 5;

    private final static Map<String, List<Reward>> database = new HashMap<>();
    private final static Map<String, Double> databaseConsiderReward = new HashMap<>();

    static {
        List<Reward> client1Rewards = new ArrayList<>();
        client1Rewards.add(new Reward("r001", "c1", "Discount 5%", java.sql.Date.valueOf(LocalDate.now()), 5));

        database.put("c1", client1Rewards);
    }

    public Reward save(Reward reward, String customerId) {
        if (!database.containsKey(customerId)) {
            database.put(customerId, new ArrayList<>());
        }
        database.get(customerId).add(reward);
        return reward;
    }

    public Optional<List<Reward>> getAllByCustomerId(String customerId) {
        if (database.containsKey(customerId)) {
            return Optional.of(database.get(customerId));
        }
        return Optional.empty();
    }

    public List<Reward> getDatabase() {
        return database.values().stream()
            .flatMap(List::stream)
            .sorted(Comparator.comparing(Reward::getDate).reversed())
            .collect(Collectors.toList());
    }

    public boolean delete(String id) {
        final Iterator<List<Reward>> iterator = database.values().iterator();
        while (iterator.hasNext()) {
            List<Reward> current = iterator.next();
            if (current.stream().filter(r -> r.getId().equals(id)).findFirst().map(current::remove).orElse(false)) {
                return true;
            }
        }
        return false;
    }

    public boolean registerAmount(String customerId, double total) {
        databaseConsiderReward.put(
            customerId,
            databaseConsiderReward.getOrDefault(customerId, 0d) + total
        );

        if (databaseConsiderReward.get(customerId) > AUTO_REWARD_LIMIT) {
            Reward reward = new Reward(
                UUID.randomUUID().toString(),
                customerId,
                "Discount Reward - Auto", java.sql.Date.valueOf(LocalDate.now()), AUTO_REWARD_DISCOUNT
            );
            save(reward, customerId);
            databaseConsiderReward.remove(customerId);
            return true;
        }

        return false;
    }
}