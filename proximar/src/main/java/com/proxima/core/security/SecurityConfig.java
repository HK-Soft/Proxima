package com.proxima.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.proximar.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

	// Non secured URLs
	http.authorizeRequests()
		.antMatchers("/", "/login", "/create-an-account")
			.permitAll()
		.antMatchers("/assets/**", "/console/**")
			.permitAll()
		.anyRequest()
			.authenticated();
	
	// console test only
	http.headers()
			.frameOptions()
				.disable();

	// Login form settings
	http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/do-login")
			.usernameParameter("username")
			.passwordParameter("password");

	// Logout setting
	http.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login");

	http.csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService);
	auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
	IAuthenticationProvider authProvider = new IAuthenticationProvider();
	authProvider.setUserDetailsService(userDetailsService);
	authProvider.setPasswordEncoder(passwordEncoder());
	return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
    }

}
