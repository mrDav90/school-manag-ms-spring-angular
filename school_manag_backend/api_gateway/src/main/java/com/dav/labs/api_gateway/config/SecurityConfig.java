package com.dav.labs.api_gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityWebFilterChain filterChain (ServerHttpSecurity http) throws Exception {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(
                        authorize -> authorize
                                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                                .pathMatchers("/api/**").authenticated()
                                .pathMatchers("/eureka/**").permitAll()
                                .pathMatchers("/swagger/**").permitAll()
                                .pathMatchers("/swagger-ui/**").permitAll()
                                .anyExchange().authenticated()
                )
                //.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );
        return http.build();
    }
}
