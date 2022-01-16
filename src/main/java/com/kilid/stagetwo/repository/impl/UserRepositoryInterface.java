package com.kilid.stagetwo.repository.impl;

import com.kilid.stagetwo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User , Integer> {
    User findByName(String name);
}
