package com.example.reservationrestapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    RestAuthEntryPoint restAuthEntryPoint;

    @Value("${URL_CLIENT}")
    private String urlClient;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        //http.csrf().disable(); DOE DIT ZEKER NIET!!!!
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.authorizeRequests().antMatchers("/authenticate").permitAll();
        http.authorizeRequests().antMatchers("/data/**").hasAnyRole("ANONYMOUS", "ADMIN", "USER");
        http.authorizeRequests().antMatchers("/protected/**").hasAnyRole("ADMIN", "USER");
        http.authorizeRequests().antMatchers("/restricted/**").hasRole("ADMIN");
        http.exceptionHandling().authenticationEntryPoint(restAuthEntryPoint);
        http.httpBasic();
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.csrf().ignoringAntMatchers("/authenticate");
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .eraseCredentials(true)
                .userDetailsService(authenticationService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    CsrfTokenRepository csrfTokenRepository() {
        return new CrossDomainCsrfTokenRepository();
    }*/

}
