package com.haphelon.auth.jpa;

import com.haphelon.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    boolean existsByEmail(String email);
}
