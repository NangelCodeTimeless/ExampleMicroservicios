//package com.example.store.customer.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import com.example.store.customer.service.InMemoryUserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityMemoryConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private InMemoryUserDetailsService inMemoryUserDetailsService;
//
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		return bCryptPasswordEncoder;
//	}
//
//	@Bean
//	public InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
//		return new InMemoryUserDetailsManager();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder2() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		auth.inMemoryAuthentication()
////		.withUser("user")
////		.password(this.passwordEncoder().encode("password"))
////		.roles("USER")
////		.and()
////		.withUser("admin")
////		.password(this.passwordEncoder().encode("admin"))
////		.roles("USER", "ADMIN");
//		auth.userDetailsService(users());
//	}
//
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user = User.builder().username("user").password(this.passwordEncoder2().encode("password"))
//				.roles("USER").build();
//		UserDetails admin = User.builder().username("admin").password(this.passwordEncoder2().encode("admin"))
//				.roles("USER", "ADMIN").build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		// La configuración predeterminada garantiza que cualquier solicitud a la
//		// aplicación
//		// se autentique con un inicio de sesión basado en formulario o autenticación
//		// básica HTTP.
//		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//		/* https://www.baeldung.com/java-config-spring-security * */
//	}
//
//}
