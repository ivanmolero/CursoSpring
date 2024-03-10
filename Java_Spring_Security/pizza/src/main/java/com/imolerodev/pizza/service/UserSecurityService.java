package com.imolerodev.pizza.service;

import com.imolerodev.pizza.persistence.entity.UserEntity;
import com.imolerodev.pizza.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService { // el implements de UserDetailsService lo hace reconocible por el ciclo de vida de spring
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found."));
        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
                .roles("ADMIN")
                .accountLocked(userEntity.getLocked())
                .accountExpired(userEntity.getDisabled())
                .build();
    }
}
