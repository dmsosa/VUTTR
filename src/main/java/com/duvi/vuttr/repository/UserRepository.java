package com.duvi.vuttr.repository;

import com.duvi.vuttr.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByUsername(String username);
    UserDetails findByEmail(String email);
}
