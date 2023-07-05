package org.vidhyaratha.employeeassetmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.vidhyaratha.employeeassetmanagement.service.UserServiceImpl;




@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserServiceImpl userService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder ()
    {
        return new BCryptPasswordEncoder(11);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/","/home",
                                        "/signin","/signup","/saveEmployee",
                                        "/styles/*", "/js/*","/images/*" ,"/getEmployeeAssets",
                                        "/showDevice","/faq",
                                        "/requestDevice","/processRequestDevice",
                                        "/returnDevice","/processReturnDevice",
                                        "/editEmployeeInformation","/editEmployee","/processEditProfile","/logout").permitAll()
                                .requestMatchers("/addNewAsset").hasAnyRole( "ADMIN","USER")
                                .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/signin")
                        .loginProcessingUrl("/signin")
                        .successForwardUrl("/getEmployeeAssets")
                        .permitAll()
                )
                .logout(
                        logout -> logout
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();

    }


    }

