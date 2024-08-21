package com.spring.cruddemo.security;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from m members where user_id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return jdbcUserDetailsManager;
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails john= User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails marry= User.builder()
//                .username("marry")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER")
//                .build();
//
//        UserDetails susan= User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE","MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john,marry,susan);
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config->config
                .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/employees").hasRole("ADMIN"));

        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
