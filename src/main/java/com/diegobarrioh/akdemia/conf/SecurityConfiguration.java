package com.diegobarrioh.akdemia.conf;

import com.diegobarrioh.akdemia.security.ApiKeyFilter;
import com.diegobarrioh.akdemia.service.ApiKeyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfiguration {

    private ApiKeyService apiKeyService;

    public SecurityConfiguration(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        return http
                .authorizeHttpRequests( authorize -> {
                            authorize.requestMatchers("/").permitAll();
                            authorize.requestMatchers("/register").permitAll();
                            authorize.requestMatchers("/user/register").permitAll();
                            authorize.requestMatchers("/register-complete").permitAll();
                            authorize.requestMatchers("/api/v1/**").permitAll();
                            authorize.requestMatchers("/h2-console/**").permitAll();
                            authorize.requestMatchers("/login").permitAll();
                            authorize.anyRequest().authenticated();
                        })
                .formLogin(form -> form.loginPage("/login"))
                .logout(logout -> logout.logoutSuccessUrl("/?logout"))
                .addFilterAfter(new ApiKeyFilter(apiKeyService),UsernamePasswordAuthenticationFilter.class)
                .build();
        // @formatter:on
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

}