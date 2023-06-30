package org.vidhyaratha.employeeassetmanagement.security;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.vidhyaratha.employeeassetmanagement.service.EmployeeServiceImpl;


//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private EmployeeServiceImpl employeeService;


    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(employeeService);
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
                                        "/styles/*", "/js/*","/images/*" ).permitAll()
                                .requestMatchers("/getEmployeeAssets",
                                        "/showDevice",
                                        "/requestDevice","/processRequestDevice",
                                        "/returnDevice","/processReturnDevice",
                                        "/editEmployeeInformation","/editEmployee","/processEditProfile","/logout").hasAnyRole( "USER","ADMIN")
                                .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/signin")
                        .loginProcessingUrl("/signin") // should point to login page
                        .successForwardUrl("/getEmployeeAssets") // must be in order thymeleaf security extras work
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

//            http.csrf(csrf -> csrf.disable())
//                    .authorizeHttpRequests(auth-> auth
//                            .requestMatchers("/","/home","/signin","/signup",
//                        "/styles/*", "/js/*","/images/*","/logout","/faq",
//                        "/processSignup","/processSignin","/saveEmployee",
//                        "/getEmployeeAssets/**","/showDevice",
//                        "/requestDevice","/*/requestDevice","/processRequestDevice",
//                        "/returnDevice","/*/returnDevice","/processReturnDevice",
//                        "/editEmployeeInformation","/*/editEmployee","/processEditProfile").permitAll()
////                        .requestMatchers("/addDevice").hasAnyRole("USER")
//                            .requestMatchers("/addDevice").hasRole("ADMIN"))
//                    .formLogin(
//                            form -> form
//                                    .loginPage("/signin")
//                                    .loginProcessingUrl("/processSignin")
//                                    .defaultSuccessUrl("/getEmployeeAssets")
//                                    .permitAll()
//                    ).logout(
//                            logout -> logout
//                                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                    .permitAll());
//            return http.build();
//        }
//    }



//
//
//        http.authorizeHttpRequests(
//                (auth) -> auth
//                .requestMatchers("/","/home","/signin","/signup",
//                        "/styles/*", "/js/*","/images/*","/logout","/faq",
//                        "/processSignup","/processSignin","/saveEmployee",
//                        "/getEmployeeAssets/**","/showDevice",
//                        "/requestDevice","/*/requestDevice","/processRequestDevice",
//                        "/returnDevice","/*/returnDevice","/processReturnDevice",
//                        "/editEmployeeInformation","/*/editEmployee","/processEditProfile").permitAll()
//                        .requestMatchers("/addDevice").hasAnyRole("USER")
//                .anyRequest().authenticated())
////                        (auth) -> auth
////                                .requestMatchers("/","/process-student").permitAll()
////                                .requestMatchers("/student-info","/student-account")
////                                .hasRole("BEGINNER").anyRequest().authenticated())
//
//                .formLogin(
//                        form -> form
//                                .loginPage("/signin")
//                                //.usernameParameter("empid").passwordParameter("password")
//                                .loginProcessingUrl("/processSignin")
//                                .successForwardUrl("/getEmployeeAssets")
//                                .permitAll()
//                )
//                .logout(logout -> logout
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .permitAll()
//                );


//        return http.build();
//
//    }
//}





//        return http
//                .authorizeRequests(configurer ->
//                        configurer
//                                .requestMatchers("/").hasRole("USER")
//                                // .requestMatchers("/leaders/**").hasRole("MANAGER")
//                                .requestMatchers("/addDevice").hasRole("ADMIN"))
//
//                .formLogin(configurer ->
//                        configurer
//                                .loginPage("/signin")
//                                //.loginProcessingUrl("/processSignin")
//                                .defaultSuccessUrl("/getEmployeeAssets")
//                                .permitAll())
//
//                .logout(configure ->
//                        configure
//                                .permitAll())
//                .exceptionHandling(configurer ->
//                        configurer
//                                .accessDeniedPage("/access-denied"))
//                .build();
//
//    }
//}

