package com.example.reservationrestapi.security;

import com.example.reservationrestapi.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    AccountRepository accountRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);
        /*        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        logger.info(request.getParameter("username"));
        redirectStrategy.sendRedirect(request, response, "http://localhost:3000/protected/account?username=" + request.getParameter("username"));*/
    }
}
