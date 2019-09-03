package com.resasoftware.nearme.configuration;

import com.resasoftware.nearme.security.jwt.JwtConfigurer;
import com.resasoftware.nearme.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/users/**").authenticated()               
                .antMatchers(HttpMethod.POST, "/users/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/users/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/users/**").authenticated()
                .antMatchers(HttpMethod.GET, "/type_users/**").authenticated()               
                .antMatchers(HttpMethod.POST, "/type_users/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/type_users/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/type_users/**").authenticated()
                .antMatchers(HttpMethod.GET, "/enterprises/**").authenticated()               
                .antMatchers(HttpMethod.POST, "/enterprises/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/enterprises/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/enterprises/**").authenticated()
                .antMatchers(HttpMethod.GET, "/comments/**").authenticated()               
                .antMatchers(HttpMethod.POST, "/comments/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/comments/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/comments/**").authenticated()
                .antMatchers(HttpMethod.GET, "/categories/**").authenticated()               
                .antMatchers(HttpMethod.POST, "/categories/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/categories/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/categories/**").authenticated()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }
}