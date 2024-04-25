package tn.esprit.pidev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Autowired
    private JwtAuthEntryPoint authEntryPoint;

    @Autowired
    public SecurityConfig(JwtAuthEntryPoint authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)//.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint) // Delegate exception handling to JwtAuthEntryPoint
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Make session STATELESS
                .and()
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                //.requestMatchers("/emplacement/**").hasRole("ADMIN")
                /* TODO ORIGINAL FIX ROLE PROBLEM */
                .requestMatchers(new AntPathRequestMatcher("/user/**"/*, "GET"*/)).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/emplacement/**")).hasRole("ADMIN") //.requestMatchers("/emplacement/**").hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/adresse/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/departement/**")).hasRole("ADMIN")
                // TODO FINISH
                .anyRequest().authenticated()
                .and().httpBasic();
        // HERE Add Filter
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // TODO CHECK IF HARDCODE ADMIN USER

    // AUTH_MANAGER
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // HERE add the JWTAuthenticationFilter Bean
    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }
}
