package com.sq3.portifoliosSq3.security;

import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.repository.UserRepository;
import com.sq3.portifoliosSq3.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);

    private TokenService tokenService;

    private UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {
            var subject = tokenService.validateToken(token);
            Optional<User> userOptional = userRepository.findByEmail(subject);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserDetails userDetails = new CustomUserDetails(user);
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }


        filterChain.doFilter(request, response);

    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Autorização");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer", "");
    }
}
