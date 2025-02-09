package com.trivago.repository;

import com.trivago.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
   boolean existsByEmail(String email);
   Boolean existsByUsername(String usrername);


   Optional<AppUser> findByUsername(String username);
}