package com.springbootdemo.boot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import javax.sql.DataSource;

@SpringBootApplication
@EnableWebSecurity
public class BootApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);

	}


	@Autowired
	private DataSource securityDataSource;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		User.UserBuilder users = User.withDefaultPasswordEncoder();
//		auth.inMemoryAuthentication()
//				.withUser(users.username("john@gmail.com").password("test").roles("CUSTOMER"))
//				.withUser(users.username("sohail@gmail.com").password("test").roles("ADMIN"));
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/user/**").hasRole("ADMIN")
				.antMatchers("/customer/**").hasRole("CUSTOMER")
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
				.and()
				.logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/accessDenied")
		;
	}
}
