package com.example.demouserauth.repository;

import com.example.demouserauth.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userrepository extends JpaRepository<user, Long> {
    user findByUsername(String username);
}
