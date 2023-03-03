package io.bookquest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .headers().frameOptions().disable().and()
                .authorizeHttpRequests(authorizeConfig -> authorizeConfig
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/api/v1/books").permitAll()
                        .requestMatchers("/api/v1/books/{idBook}/questions").permitAll()
                        .requestMatchers("/h2-console/**").permitAll())
                .build();
    }
}