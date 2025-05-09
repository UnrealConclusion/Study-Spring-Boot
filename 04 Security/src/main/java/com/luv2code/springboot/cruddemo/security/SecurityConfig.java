package com.luv2code.springboot.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	/* Hardcoded User Authentication
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails john = User.builder()
			.username("john")
			.password("{noop}test123")
			.roles("EMPLOYEE").build();
        
		UserDetails mary = User.builder()
			.username("mary")
			.password("{noop}test123")
			.roles("EMPLOYEE", "MANAGER").build();
        
		UserDetails susan = User.builder()
			.username("susan")
			.password("{noop}test123")
			.roles("EMPLOYEE", "MANAGER", "ADMIN").build();
        
		return new InMemoryUserDetailsManager(john, mary, susan);
	}
	*/

	/* Database User Authentication */
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		// query to find users by username 		
		jdbcUserDetailsManager.setUsersByUsernameQuery(
			"SELECT user_id, pw, active FROM members WHERE user_id=?"
		);

		// query to find authorities by username 
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
			"SELECT user_id, role FROM roles WHERE user_id=?"
		);

		return jdbcUserDetailsManager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> configurer
			.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE") // Read all employees
			.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE") // Read a single employee
			.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER") // Create an employee
			.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER") // Update an employee
			.requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER") // Patch an employee
			.requestMatchers(HttpMethod.DELETE, "/api/employees").hasRole("ADMIN") // Delete an employee
		);

		// use HTTP basic authentication 
		http.httpBasic(Customizer.withDefaults());
	
		// disable Cross Site Request Forgery (CSRF)
		// in general not required for stateless REST APIs
		http.csrf(csrf -> csrf.disable());
	
		return http.build();
	}
}
