package com.minData.W2m.config;

import com.minData.W2m.domain.filters.SecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilter securityFilter() {
        return new SecurityFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().and().contentSecurityPolicy("frame-ancestors 'self' https://javabydeveloper.com hhtps://*.javabydeveloper.com").and().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate", "/h2-console/**", "/v2/api-docs", "/v3/api-docs", "/swagger-resources/**", "/swagger-ui/**").permitAll()
                .antMatchers("/superHeros/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(securityFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
