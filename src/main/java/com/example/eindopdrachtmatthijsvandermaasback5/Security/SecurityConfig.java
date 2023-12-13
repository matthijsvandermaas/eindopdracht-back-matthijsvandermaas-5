package com.example.eindopdrachtmatthijsvandermaasback5.Security;


import com.example.eindopdrachtmatthijsvandermaasback5.Repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public SecurityConfig(JwtService service, UserRepository userRepos) {
        this.jwtService = service;
        this.userRepository = userRepos;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService udService, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(udService);
        return new ProviderManager(auth);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.GET, "/review").permitAll()
                        .requestMatchers(HttpMethod.POST, "/review").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reservation").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/reservation").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/reservation/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/product").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/product/{id}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/product").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/product/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/profile").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/profile/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/profile").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/profile/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/single/uploadDB").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/downloadFromDB/{fileName}").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()


                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}