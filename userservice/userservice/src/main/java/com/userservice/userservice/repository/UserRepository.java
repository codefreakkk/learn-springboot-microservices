package com.userservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.userservice.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
