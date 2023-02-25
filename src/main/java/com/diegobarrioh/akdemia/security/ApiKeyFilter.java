package com.diegobarrioh.akdemia.security;

import com.diegobarrioh.akdemia.domain.entity.ApiKey;
import com.diegobarrioh.akdemia.domain.repository.ApiKeyRepository;
import com.diegobarrioh.akdemia.service.ApiKeyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Log4j2
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer ";

    ApiKeyService apiKeyService;

    public ApiKeyFilter(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        String apiKey = request.getParameter("apikey");
        log.info("apikey provided: {}", apiKey);

        if (apiKey == null) {
            filterChain.doFilter(request,response);
        } else {
            ApiKey key = apiKeyService.getByApikeyValue(apiKey);
            if (key == null) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-type", "text/plain;charset=utf-8");
                response.getWriter().println("No tienes una apikey válida 🤖🦾");
                return;
            }

            var newContext = SecurityContextHolder.createEmptyContext();
            newContext.setAuthentication(new ApiKeyAuthentication(key));
            SecurityContextHolder.setContext(newContext);
            filterChain.doFilter(request, response);
        }
    }
}


