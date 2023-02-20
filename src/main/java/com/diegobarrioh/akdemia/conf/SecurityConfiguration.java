package com.diegobarrioh.akdemia.conf;

import com.diegobarrioh.akdemia.security.JwtTokenFilter;
import com.diegobarrioh.akdemia.service.ApiKeyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfiguration {

    @Autowired
    private ApiKeyService apiKeyService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.debug("securityFilterChain:");

        // @formatter:off
        http
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers("/","/register","/user/register","/register-complete","/h2-console/**","/api/v1/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/?logout")
                )
        //disable CSRF (cross site request forgery)
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
        //no session will be created or used by spring security
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
         //apply JWT
                .addFilter(new JwtTokenFilter())
                .authenticationProvider(apiKeyService);

        // @formatter:on
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

}