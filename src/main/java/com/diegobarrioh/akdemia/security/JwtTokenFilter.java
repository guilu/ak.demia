package com.diegobarrioh.akdemia.security;

import com.diegobarrioh.akdemia.domain.dto.RestApiError;
import com.diegobarrioh.akdemia.security.authentication.CustomTokenAuthentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            manageTokenAuthentication(request);
            filterChain.doFilter(request, response);
        } catch (BadCredentialsException  bce) {
            sendError(response, HttpStatus.UNAUTHORIZED, bce.getMessage());
        } catch (CredentialsExpiredException cee) {
            sendError(response, HttpStatus.FORBIDDEN, cee.getMessage());
        }
    }

    private void sendError(HttpServletResponse httpServletResponse, HttpStatus httpStatus, String message) throws IOException {
        //Clear security context
        SecurityContextHolder.clearContext();
        //Built response
        RestApiError error = new RestApiError(httpStatus,message, message);
        sendMessage(httpServletResponse, httpStatus, error);
    }

    private void sendMessage(HttpServletResponse httpServletResponse, HttpStatus httpStatus, Object object) throws IOException {
        httpServletResponse.setStatus(httpStatus.value());
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(object));
    }

    private String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if( bearerToken != null && bearerToken.startsWith(TOKEN_TYPE) ) {
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }

    private HttpServletRequest asHttp(ServletRequest servletRequest) {
        return (HttpServletRequest) servletRequest;
    }

    private HttpServletResponse asHttp(ServletResponse servletResponse) {
        return (HttpServletResponse) servletResponse;
    }

    private void manageTokenAuthentication( HttpServletRequest httpServletRequest) {
        String token = resolveToken(httpServletRequest);
        if (StringUtils.hasLength(token)) {
            SecurityContextHolder.getContext().setAuthentication(new CustomTokenAuthentication(token));
        }
    }

}


