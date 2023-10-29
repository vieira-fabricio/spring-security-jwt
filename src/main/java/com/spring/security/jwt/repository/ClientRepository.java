package com.spring.security.jwt.repository;

import com.spring.security.jwt.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
