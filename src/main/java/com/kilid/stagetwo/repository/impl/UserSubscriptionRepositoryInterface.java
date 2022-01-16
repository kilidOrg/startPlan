package com.kilid.stagetwo.repository.impl;

import com.kilid.stagetwo.model.User_Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepositoryInterface extends JpaRepository<User_Subscription , Integer> {
}
