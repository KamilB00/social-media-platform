package com.socialmediaplatform.infrastructure.security;

import com.socialmediaplatform.infrastructure.security.jwt.JwtFilterConfiguration;
import com.socialmediaplatform.infrastructure.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${management.server.port}")
    private int managementPort;

    @Autowired
    private JwtProvider jwtProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Cross site request forgery
        http.csrf().disable();

        // Session will not be created or used by Spring Security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Entry points
        http.authorizeRequests()
                .antMatchers("/api/user/signin").permitAll()
                .antMatchers("/api/user/signup").permitAll()
                .requestMatchers(checkPort(managementPort)).permitAll()
                .anyRequest()
                .authenticated();
        // Applying JWT
        http.apply(new JwtFilterConfiguration(jwtProvider));
    }

    private RequestMatcher checkPort(final int port){
        return (HttpServletRequest request) -> port == request.getLocalPort();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
