package com.crio.stayease.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crio.stayease.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    boolean existsByEmail(String email);

}