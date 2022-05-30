package com.example.store.customer.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	String CLIENT_ID = "EB4AE6E7966442CBA26C201205E0AD16";

	String CLIENT_SECRET = "F3CE53C90AD3438A9312834A4B5BA901";

	String AUTH_SERVER = "https://login.iplus.tech/oauth2/token";

	@Bean
	public Docket apiDocket() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.store.customer"))
				// .paths(PathSelectors.ant("/products/*"))
				.paths(PathSelectors.any()).build();
//				.securitySchemes(Arrays.asList(securityScheme()))
//				.securityContexts(Arrays.asList(securityContext()));
	}

//	@Bean
//	public SecurityConfiguration security() {
//		return SecurityConfigurationBuilder.builder().clientId(CLIENT_ID).clientSecret(CLIENT_SECRET)
//							.scopeSeparator(" ").useBasicAuthenticationWithAccessCodeGrant(true).build();
//	}
//
//	private SecurityScheme securityScheme() {
//		GrantType grantType = new AuthorizationCodeGrantBuilder()
//				.tokenEndpoint(new TokenEndpoint(AUTH_SERVER, "oauthtoken"))
//				.tokenRequestEndpoint(new TokenRequestEndpoint(AUTH_SERVER, CLIENT_ID, CLIENT_SECRET))
//				.build();
//
//		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth").grantTypes(Arrays.asList(grantType))
//				.scopes(Arrays.asList(scopes())).build();
//		return oauth;
//	}
//
//	private AuthorizationScope[] scopes() {
//		AuthorizationScope[] scopes = { new AuthorizationScope("read", "for read operations"),
//				new AuthorizationScope("write", "for write operations"),
//				new AuthorizationScope("foo", "Access foo API") };
//		return scopes;
//	}
//
//	private SecurityContext securityContext() {
//		return SecurityContext.builder()
//				.securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
//				.forPaths(PathSelectors.any()).build();
//	}

	private ApiInfo getApiInfo() {
//		  return new ApiInfoBuilder()
//	                .title("Service User")
//	                .version("1.0")
//	                .license("Apache License Version 2.0")
//	                .build();
		return new ApiInfo("My REST API", "Store customer description of API.", "API CUSTOMER", "Terms of service",
				new Contact("Jean", "www.customer.com", "store@company.com"), "License of API", "API license URL",
				Collections.emptyList());
	}

	// http://localhost:8080/spring-security-rest/api/v2/api-docs
	// http://localhost:8080/sswagger-ui.html
}
