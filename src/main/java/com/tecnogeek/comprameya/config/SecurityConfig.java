/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author jzelaya
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {    
        
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception
    {
        registry.userDetailsService(customUserDetailsService);
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.
            ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/images/**");
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/","/index","/login","login/form**","/register","/logout").permitAll()
                .antMatchers("/publicacion","/publicacion/agregarAnuncio").hasRole("USER")
                    .antMatchers("/admin","/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .deleteCookies("remove")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll();
    }
    
}
