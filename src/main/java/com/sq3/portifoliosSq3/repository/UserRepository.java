package com.sq3.portifoliosSq3.repository;

import com.sq3.portifoliosSq3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
