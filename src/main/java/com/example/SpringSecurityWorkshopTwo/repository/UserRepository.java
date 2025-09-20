package com.example.SpringSecurityWorkshopTwo.repository;

import com.example.SpringSecurityWorkshopTwo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
