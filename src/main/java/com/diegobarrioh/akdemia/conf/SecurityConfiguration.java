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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
                .logout(logout -> logout.logoutSuccessUrl("/?logout")
                )
        //disable CSRF (cross site request forgery)
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
         //apply JWT
                .addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();

        // @formatter:on
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(11);
    }

}