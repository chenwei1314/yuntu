package com.cw.yuntu.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String user1 = "cw";

    private final String user2 = "cwadmin";



    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(
                new BCryptPasswordEncoder())
                .withUser(user1)
                .password(new BCryptPasswordEncoder().encode(user1))
                .roles("USER").and().withUser(user2).
                password(new BCryptPasswordEncoder().encode(user2))
                .roles("USER", "ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭CSRF
        http.csrf().disable();
        http.httpBasic().and().authorizeRequests().anyRequest()
                .fullyAuthenticated();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/actuator/hystrix.stream", "/turbine.stream");
    }
}
