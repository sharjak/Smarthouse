package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AuthServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("userpwd").roles("USER")
                .and()
                .withUser("admin").password("adminpwd").roles("ADMIN")
                .and()
                .withUser("soncrserv").password("soncrserv").roles("CLIENT");

        auth.parentAuthenticationManager(authenticationManager);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .formLogin().permitAll()
                .and()
                .anonymous().disable()
                .csrf().disable()
                .requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access", "/oauth/token")
                .and()
                .authorizeRequests().anyRequest().authenticated();
        // @formatter:on
    }
}
