package org.springboot.pet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
//    @Autowired
//    private JwtAuthFilter authFilter

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserInfoService();
//    }

//    @Bean

}
