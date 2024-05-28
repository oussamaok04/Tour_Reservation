package com.example.reservationtours.Security;

import com.example.reservationtours.Security.Service.implementations.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/", "/tours/all", "/tours/details", "/webjars/**").permitAll()
                )
                .authorizeHttpRequests((requests) ->
                    requests.requestMatchers("/tours/save", "/tours/edit", "/tours/delete").hasAnyAuthority("ADMIN")

                )
                .authorizeHttpRequests((requests) -> {
                    requests.anyRequest().authenticated();
                })
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll())
                .logout((logout) -> logout.permitAll().logoutUrl("/login?logout"))
                .exceptionHandling(handler -> {
                    handler.accessDeniedPage("/security/403");
                });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
//
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity security) throws Exception {
//        security.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/admin/home","/admin/conferences","/admin/conferences/add").authenticated()
//                .requestMatchers("/admin/users","/admin/salles","/admin/users/**","/admin/salles/**","/admin/conferences/ticket/**").hasAnyRole("ADMIN").anyRequest().permitAll()).cors(AbstractHttpConfigurer::disable).formLogin(f -> f.loginPage("/admin/login").permitAll().defaultSuccessUrl("/admin/home").failureHandler(new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                response.sendRedirect("/admin/login?error="+exception.getMessage());
//            }
//        }));

//        security.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.requestMatchers("/*jpg","/*png","/admin/signup","/api/**").permitAll()
//                .requestMatchers("/admin/users","/admin/salles").hasAnyRole("ADMIN").anyRequest().authenticated()).cors(AbstractHttpConfigurer::disable).formLogin(f -> f.loginPage("/admin/login").permitAll().defaultSuccessUrl("/admin/home").failureHandler(new AuthenticationFailureHandler() {
//            @Override
//            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                response.sendRedirect("/admin/login?error="+exception.getMessage());
//            }
//        }));
//
//        return security.build();
//    }

}
