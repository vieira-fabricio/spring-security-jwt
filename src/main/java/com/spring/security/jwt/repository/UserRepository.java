package com.spring.security.jwt.repository;

import com.spring.security.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {

    UserDetails findByUserName(String userName);

    boolean existsByUserName(String userName);
}
