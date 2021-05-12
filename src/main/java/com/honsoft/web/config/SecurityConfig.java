package com.honsoft.web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebSecurity (debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	//@Override
	//protected void configure(HttpSecurity http) throws Exception {
	//	// http.authorizeRequests()
	//}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("user").password(bCryptPasswordEncoder().encode("password")).roles("USER");
		auth
        .jdbcAuthentication()
        .dataSource(dataSource())
        .passwordEncoder(bCryptPasswordEncoder());
	}
	
	@Bean
	protected PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DataSource dataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/sakila");
		ds.setUsername("shoppingnt");
		ds.setPassword("Shoppingnt2021!@");
		return ds;
	}
}