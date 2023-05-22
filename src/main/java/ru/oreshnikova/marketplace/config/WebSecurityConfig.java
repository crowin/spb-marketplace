package ru.oreshnikova.marketplace.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class WebSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().sameOrigin().and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/*", "/registration/**", "/login/**", "/view/**", "/search/**").permitAll()
                        .requestMatchers("/view/**", "/receipt/**", "/search/**", "/view/**", "/product/**", "/product/purchase/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/list/**", "/suppliers/**", "/product/delete/**", "/product/edit/**").hasRole("ADMIN").
                        anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessUrl("/").permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }
}