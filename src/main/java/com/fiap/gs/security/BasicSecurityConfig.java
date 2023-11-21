package com.fiap.gs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity(debug = true)
public class BasicSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

		http.httpBasic();
		http
				.csrf()
				.disable()
				.cors()
				.disable()
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/usuarios/cadastrar").permitAll()
						.requestMatchers("/usuarios/logar").permitAll()
						.requestMatchers("/error").permitAll()
						.anyRequest().authenticated()
				);

//		http.authenticationManager(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));

		return http.build();
	}

	private AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
