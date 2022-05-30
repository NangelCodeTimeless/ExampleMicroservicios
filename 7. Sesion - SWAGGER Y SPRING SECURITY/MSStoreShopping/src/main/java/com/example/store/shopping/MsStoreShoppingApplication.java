package com.example.store.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.store.shopping.security.JWTAuthorizationFilter;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
//@EnableFeignClients(basePackages = "com.example.store.shopping.service.client")
@EnableHystrix
@EnableHystrixDashboard
public class MsStoreShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStoreShoppingApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests().antMatchers("/shopping/**", "/user")
					// .antMatchers(HttpMethod.POST, "/user")
					.permitAll().anyRequest().authenticated();
		}
	}
}
