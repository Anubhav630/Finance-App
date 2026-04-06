package com.financeapp.FinanceApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/dashboard/**").hasAnyRole("VIEWER","ANALYST","ADMIN")
                        .requestMatchers("/records/**").hasAnyRole("ANALYST","ADMIN")
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = org.springframework.security.core.userdetails.User
                .withUsername("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails analyst = org.springframework.security.core.userdetails.User
                .withUsername("analyst")
                .password("{noop}analyst123")
                .roles("ANALYST")
                .build();

        UserDetails viewer = org.springframework.security.core.userdetails.User
                .withUsername("viewer")
                .password("{noop}viewer123")
                .roles("VIEWER")
                .build();

        return new InMemoryUserDetailsManager(admin, analyst, viewer);
    }
}
