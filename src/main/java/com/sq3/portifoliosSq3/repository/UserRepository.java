package com.sq3.portifoliosSq3.repository;

import com.sq3.portifoliosSq3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
