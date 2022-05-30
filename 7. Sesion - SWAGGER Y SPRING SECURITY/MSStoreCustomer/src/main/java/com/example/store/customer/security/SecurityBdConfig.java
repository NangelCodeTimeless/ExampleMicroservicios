package com.example.store.customer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.store.customer.service.InBdUserDetailsService;

@Configuration
@EnableWebSecurity
//Para habilitar la seguridad HTTP en Spring, necesitamos extender WebSecurityConfigurerAdapter
//para proporcionar una configuración predeterminada en el método configure (HttpSecurity http) :
public class SecurityBdConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private InBdUserDetailsService userDetailsService;

	// La mayoría de los otros mecanismos, como MD5PasswordEncoder y
	// ShaPasswordEncoder, utilizan algoritmos más débiles y ahora están en desuso.

	// BCrypt, sin embargo, generará internamente una sal aleatoria en su lugar. Es
	// importante entender esto porque significa que cada llamada
	// tendrá un resultado diferente, por lo que solo necesitamos codificar la
	// contraseña una vez.
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// La configuración predeterminada garantiza que cualquier solicitud a la
		// aplicación
		// se autentique con un inicio de sesión basado en formulario o autenticación
		// básica HTTP.
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		/* https://www.baeldung.com/java-config-spring-security * */
	}

}