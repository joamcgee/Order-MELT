package com.pizza.MELT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception { //allocates authorization privileges to UserRoles
        return http
                .authorizeRequests(auth -> {
                auth.antMatchers("/").hasAnyRole(UserRole.ADMIN.name(), UserRole.PUBLISHER.name());
                auth.antMatchers("/delete/**").hasRole(UserRole.ADMIN.name()); //  /**, meaning it will apply to all request

                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()) //encodes the user-id/password using BASE64
                .build();
    }
    @Bean
    protected InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}1234")
                .roles(UserRole.ADMIN.name())
                .build();
        UserDetails publisher = User.builder()
                .username("publisher")
                .password("{noop}123")
                .roles(UserRole.PUBLISHER.name())
                .build();
        UserDetails readOnly = User.builder()
                .username("read_only")
                .password("{noop}12")
                .roles(UserRole.READ_ONLY.name())
                .build();

        return new InMemoryUserDetailsManager(admin,publisher,readOnly);
    }
}
