package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User authenticatedUser = userRepository.findUserByUsername(username).stream().findFirst().orElse(null);
        System.out.println("Here is the authenticatedUser : "+authenticatedUser);
        if (authenticatedUser==null) {
            throw  new UsernameNotFoundException("Authenticated User doesn't exist !! ");
        }
        // Create an object of UserDetails Type
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) org.springframework.security.core.userdetails.User.builder()
                .username(authenticatedUser.getUsername())
                .password(authenticatedUser.getPassword())
                .roles("USER").build();
        return user;
    }
}
