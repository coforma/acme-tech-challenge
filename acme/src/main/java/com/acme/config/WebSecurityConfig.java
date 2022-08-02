package com.acme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.acme.common.AppUserDetailsService;

@Configuration
public class WebSecurityConfig { 

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AppUserDetailsService appUserDetailsService) throws Exception {
    	AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    	authenticationManagerBuilder.userDetailsService(appUserDetailsService);
    	
    	http.authorizeHttpRequests((authz) -> authz.antMatchers("/","index.html","/login","/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll().anyRequest().authenticated()).formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/swagger-ui/index.html", true).and()
        .authenticationManager(authenticationManagerBuilder.build());
        
        return http.csrf().disable().build();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    };
}