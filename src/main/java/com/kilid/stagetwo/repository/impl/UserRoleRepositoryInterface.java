package com.kilid.stagetwo.repository.impl;

import com.kilid.stagetwo.model.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepositoryInterface extends JpaRepository<User_Role , Integer> {
    //List<User_Role> findByUser_id(Integer id);
}
