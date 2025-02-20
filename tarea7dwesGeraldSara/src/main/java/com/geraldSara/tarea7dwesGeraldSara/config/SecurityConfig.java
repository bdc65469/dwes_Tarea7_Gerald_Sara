package com.geraldSara.tarea7dwesGeraldSara.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private GestionRoles gestor;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/", "/index", "/plantas/listadoPlantas", "/cliente/formularioRegistro", "/cliente/registrarCliente", "/cliente/registrado", "/css/**", "/imagenes/**").permitAll() // Permitir acceso libre a la página de inicio
	            .requestMatchers("/mensajes", "/ejemplares", "/cliente", "/pedidos", "/gestion", "/menu").hasAnyRole("REGISTRADO",  "ADMIN")
	            .requestMatchers("/cliente").hasAnyRole("CLIENTE", "ADMIN", "REGISTRADO")
	            .requestMatchers("/plantas", "/registrarusuario").hasAnyRole("ADMIN")
	            .anyRequest().authenticated() // El resto de rutas requieren autenticación
	            
	        )
	        //.csrf(csrf -> csrf.ignoringRequestMatchers("/cliente/registrarCliente"))
	        .formLogin(login -> login
	        	    .loginPage("/login")
	        	    .loginProcessingUrl("/process_login") // Spring Security manejará este endpoint
	        	    .successHandler(gestor)
	        	    .failureUrl("/login?error=true") // Si falla, redirige a login con error
	        	    .permitAll()
	        	)
	        .logout(logout -> logout
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login?logout")
	            .permitAll()
	        ).sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);


	    return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userDetailsService)
	        throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .userDetailsService(userDetailsService)
	            .passwordEncoder(passwordEncoder())
	            .and()
	            .build();
	}

	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
