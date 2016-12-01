/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tecnogeek.comprameya.config;

import com.tecnogeek.comprameya.components.RestAccessDeniedHandler;
import com.tecnogeek.comprameya.components.RestAuthenticationFailureHandler;
import com.tecnogeek.comprameya.components.RestUnauthorizedEntryPoint;
import com.tecnogeek.comprameya.service.CustomAuthSuccessHandler;
import com.tecnogeek.comprameya.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 *
 * @author jzelaya
 */
@Configuration
@EnableWebSecurity
//@EnableRedisHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String REMEMBER_ME_KEY = "rememberme_key";
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private RestUnauthorizedEntryPoint restAuthenticationEntryPoint;
    
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    
    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    
    @Autowired
    private CustomAuthSuccessHandler customAuthSuccessHandler;

    @Autowired
    private Environment environment;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.
                ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/images/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication();
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeRequests()                
                .antMatchers("/",
                        "/index",
                        "/login",
                        "login/form**",
                        "/register",
                        "/logout",
                        "/auth/**",
                        "/connect/**",
                        "/signin/**",
                        "/login",
                        "/signin/**",
                        "/signup/**",
                        "/user/register/**",
                        "/modal",
                        "/angular",
                        "/publicacion/getAnuncios/**",
                        "/publicacion/getAnunciosByCat/**",
                        "/publicacion/getAnunciosByMatch/**",
                        "/publicacion/getAnunciosSinPaginar",
                        "/publicacion/getPublicacionById/**",
                        "/publicacion/getTotalPaginas/**",
                        "/publicacion/getTotalAnuncios/**",
                        "/comentario/getComentarios/**",
                        "/recurso/getRecursos/**",
                        "/categoria/**",
                        "/ubicacion/**",                        
                        "/cmpyWebSocket/**",
                        "/SpiderWebService/**",
                        "/tiendas/aleatorias/"
                ).permitAll()
                .antMatchers(
                        "/publicacion",
                        "/publicacion/agregarAnuncio",
                        "/publicacion/getMisAnuncios",
                        "/comentario",
                        "/comentario/agregarComentario",
                        "/notificacion/**",
                        "/mensaje/**"                        
                ).hasRole("USER")
                .antMatchers(
                        "/admin",
                        "/admin/**"
                ).hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(restAuthenticationEntryPoint)
                    .accessDeniedHandler(restAccessDeniedHandler)
                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/login/authenticate")
//                    .failureUrl("/login?error=bad_credentials")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .successHandler(customAuthSuccessHandler)
//                    .failureHandler(restAuthenticationFailureHandler)
//                .and()
                    .logout()
                    .deleteCookies("remove")
                    .invalidateHttpSession(true)
//                    .logoutSuccessUrl("/")
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                    .permitAll()
                .and()
                    .httpBasic()
                .and()
                    .apply(new SpringSocialConfigurer()
                            .alwaysUsePostLoginUrl(true)
                            .postLoginUrl("/")
                    )
//                .and()
//                    .rememberMe()
//                    .rememberMeServices(rememberMeServices())
//                    .key(REMEMBER_ME_KEY)
                .and();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
//    public RememberMeServices rememberMeServices() {
//        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("password", userDetailsService());
//        rememberMeServices.setCookieName(environment.getProperty("local.cookieName"));
//        rememberMeServices.setParameter("rememberMe");
//        return rememberMeServices;
//    }

}
