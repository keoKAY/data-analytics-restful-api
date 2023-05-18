package com.istad.dataanalyticrestfulapi.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // basically we have to create three type of beans
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // write the code in order to configure the security.

        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/authentication/**","/api/v1/file-service/**","/api/v1/mail/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();

    }


    // postgres , testing ( H2 database  , also inmemory database)
    // 1. user credentials
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        // create three users

        UserDetails user1 = User.builder()
                .username("veasna")
                .roles("USER")
                .password("12345")
                .build();

        UserDetails user2 = User.builder()
                .username("samnang")
                .roles("USER")
                .password("12345")
                .build();
        UserDetails user3 = User.builder()
                .username("somboleap")
                .roles("USER")
                .password("12345")
                .build();

        return new InMemoryUserDetailsManager(user1,user2, user3) ;
    }


    // NoOp password encoder
    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }




}
