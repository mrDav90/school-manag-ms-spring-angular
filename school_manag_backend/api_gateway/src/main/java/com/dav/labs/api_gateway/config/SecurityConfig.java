package com.dav.labs.api_gateway.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private final JwtAuthConverter jwtAuthConverter;
//    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
//        this.jwtAuthConverter = jwtAuthConverter;
//    }
    @Bean
    public SecurityWebFilterChain filterChain (ServerHttpSecurity http) throws Exception {
    //public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                //.csrf(AbstractHttpConfigurer::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                //.cors(Customizer.withDefaults())
                //.cors(ServerHttpSecurity.CorsSpec::disable)
                .authorizeExchange(
                        authorize -> authorize
                                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                                .pathMatchers("/api/**").authenticated()
                                .pathMatchers("/eureka/**").permitAll()
                                .pathMatchers("/swagger/**").permitAll()
                                .pathMatchers("/swagger-ui/**").permitAll()
                                //.anyExchange().authenticated()
                                .anyExchange().authenticated()
                        //.hasRole(ADMIN)
                )
                //.formLogin(Customizer.withDefaults(
                //.oauth2Login(Customizer.withDefaults())
                //.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );
        //.logout(logout -> logout.clearAuthentication(true).invalidateHttpSession(true));
        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsWebFilter() {
//
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(Arrays.asList("*"));
//        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(Arrays.asList("*"));
//        //config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        if (!CollectionUtils.isEmpty(config.getAllowedOrigins()) || !CollectionUtils.isEmpty(config.getAllowedMethods())) {
//            source.registerCorsConfiguration("/api/**", config);
//        }
//
//        return source;
//    }
}
