package com.adepuu.montrack.infrastructure.config;

import com.adepuu.montrack.usecase.auth.GetUserAuthDetailsUsecase;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.SecurityFilterChain;

// import javax.crypto.SecretKey;
// import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Log
public class SecurityConfig {
  private final GetUserAuthDetailsUsecase getUserAuthDetailsUsecase;
  private final JwtConfigProperties jwtConfigProperties;
  private final PasswordEncoder passwordEncoder;
  private final RsaKeyConfigProperties rsaKeyConfigProperties;

  public SecurityConfig(GetUserAuthDetailsUsecase getUserAuthDetailsUsecase, JwtConfigProperties jwtConfigProperties, PasswordEncoder passwordEncoder, RsaKeyConfigProperties rsaKeyConfigProperties) {
    this.getUserAuthDetailsUsecase = getUserAuthDetailsUsecase;
    this.jwtConfigProperties = jwtConfigProperties;
    this.passwordEncoder = passwordEncoder;
    this.rsaKeyConfigProperties = rsaKeyConfigProperties;
  }

  @Bean
  public AuthenticationManager authManager() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(getUserAuthDetailsUsecase);
    authProvider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(authProvider);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    //  Define public routes
                    .requestMatchers("/error/**").permitAll()
                    .requestMatchers("/api/v1/auth/login").permitAll()
                    .requestMatchers("/api/v1/users/register").permitAll()

                    //  Define rest of the routes to be private
                    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt.decoder(jwtDecoder()))
            )
            .userDetailsService(getUserAuthDetailsUsecase)
            .build();
  }

//  @Bean
//  public JwtDecoder jwtDecoder() {
//      SecretKey secretKey = new SecretKeySpec(jwtConfigProperties.getSecret().getBytes(), "HmacSHA256");
//      return NimbusJwtDecoder.withSecretKey(secretKey).build();
//  }
//
//  @Bean
//  public JwtEncoder jwtEncoder() {
//      SecretKey secretKey = new SecretKeySpec(jwtConfigProperties.getSecret().getBytes(), "HmacSHA256");
//      return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
//  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(rsaKeyConfigProperties.publicKey()).build();
  }

  @Bean
  JwtEncoder jwtEncoder() {
    JWK jwk = new RSAKey.Builder(rsaKeyConfigProperties.publicKey()).privateKey(rsaKeyConfigProperties.privateKey()).build();
    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }
}