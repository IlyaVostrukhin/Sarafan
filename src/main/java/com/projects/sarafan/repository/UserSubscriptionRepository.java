package com.projects.sarafan.repository;

import com.projects.sarafan.domain.User;
import com.projects.sarafan.domain.UserSubscription;
import com.projects.sarafan.domain.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {
    List<UserSubscription> findBySubscriber(User user);
}
