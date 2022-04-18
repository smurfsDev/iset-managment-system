package com.projetIntegraion.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("youssef").password(passwordEncoder.encode("admin")).roles("STUDENT",
                "USER");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("youssef")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().defaultSuccessUrl("/listeDcc", true);
        http.authorizeRequests().antMatchers("/showCreateDcc", "/saveDcc", "/modifierDcc", "/updateDcc").hasAnyRole("STUDENT");
        http.authorizeRequests().antMatchers("/listeDcc")
                .hasAnyRole("ADMIN", "STUDENT", "USER");
        http.authorizeRequests()
                .antMatchers("/deleteDcc")
                .hasAnyRole("ADMIN","STUDENT");
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();
    }
}
