package com.projects.sarafan.service;

import com.projects.sarafan.domain.User;
import com.projects.sarafan.domain.UserSubscription;
import com.projects.sarafan.repository.UserDetailsRepository;
import com.projects.sarafan.repository.UserSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;

    @Autowired
    public ProfileService(UserDetailsRepository userDetailsRepository, UserSubscriptionRepository userSubscriptionRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userSubscriptionRepository = userSubscriptionRepository;
    }

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subscriptions = channel.getSubscribers()
                .stream()
                .filter(subscription -> subscription.getSubscriber().equals(subscriber))
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
            channel.getSubscribers().removeAll(subscriptions);
        }

        return userDetailsRepository.save(channel);
    }

    public List<UserSubscription> getSubscribers(User channel) {

        List<UserSubscription> byChannel = userSubscriptionRepository.findByChannel(channel);
        return byChannel;
    }

    public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
        UserSubscription subscription = userSubscriptionRepository.findByChannelAndSubscriber(channel, subscriber);
        subscription.setActive(!subscription.isActive());

        return userSubscriptionRepository.save(subscription);
    }
}
