package com.scaler.blogapi.security;

import com.scaler.blogapi.security.authtokens.AuthTokenAuthenticationFilter;
import com.scaler.blogapi.security.authtokens.AuthTokenService;
import com.scaler.blogapi.security.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthTokenService authTokenService;

    public AppSecurityConfig(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: In production setup these should not be disabled
        http.csrf().disable().cors().disable();

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/articles").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(new JWTAuthenticationFilter(), AnonymousAuthenticationFilter.class);
        http.addFilterBefore(new AuthTokenAuthenticationFilter(authTokenService), AnonymousAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
