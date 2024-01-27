package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.repository.UserRepository;
import com.sq3.portifoliosSq3.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado com o email: " + username));
        return new CustomUserDetails(user);
    }
}
