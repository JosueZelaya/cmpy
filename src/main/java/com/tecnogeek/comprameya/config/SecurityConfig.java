/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tecnogeek.comprameya.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

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
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
            .userDetailsService(customUserDetailsService);
//            .passwordEncoder(passwordEncoder());
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.
            ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/images/**")
                .antMatchers("/categoria/**")
                .antMatchers("/ubicacion/**");
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http
            .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/",
                            "/index",
                            "/login",
                            "login/form**",
                            "/register",
                            "/logout",
                            "/user",
                            "/modal",
                            "/angular",
                            "/publicacion/getAnuncios/**",
                            "/publicacion/getAnunciosSinPaginar",
                            "/publicacion/getPublicacionById/**",
                            "/publicacion/getTotalPaginas/**",
                            "/publicacion/getTotalAnuncios/**").permitAll()
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
                    .permitAll()
                .and()
                    .apply(new SpringSocialConfigurer());
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
 
//    @Bean
//    public SocialUserDetailsService socialUserDetailsService() {
//        return new SocialCustomUserDetailsService();
//    }
// 
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailsService();
//    }
}
