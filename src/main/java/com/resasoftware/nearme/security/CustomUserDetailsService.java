package com.resasoftware.nearme.security;

import com.resasoftware.nearme.entities.User;
import com.resasoftware.nearme.repository.UserRepository;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository users;

    public CustomUserDetailsService(UserRepository users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	BCryptPasswordEncoder encoder = passwordEncoder();
        Optional<User> user = users.findByUsername(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("User Name " + username + "Not Found");
        }
        return new org.springframework.security.core.userdetails.User(user.get().getUsername(), encoder.encode(user.get().getPassword()),user.get().getAuthorities());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
