package com.imolerodev.pizza.service;

import com.imolerodev.pizza.persistence.entity.UserEntity;
import com.imolerodev.pizza.persistence.entity.UserRoleEntity;
import com.imolerodev.pizza.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService { // el implements de UserDetailsService lo hace reconocible por el ciclo de vida de spring
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found."));
        String[] roles = userEntity.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);
        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
//                .roles(roles)
                .authorities(this.grantedAuthorities(roles))
                .accountLocked(userEntity.getLocked())
                .accountExpired(userEntity.getDisabled())
                .build();
    }

    private String[] getAuthorities(String role) {
        if ("ADMIN".equals(role) || "CUSTOMER".equals(role)) {
            return new String[] {"random_orders"};
        }
        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            for (String authority: getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
}
