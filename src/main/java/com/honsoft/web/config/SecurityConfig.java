package com.honsoft.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

import com.honsoft.web.service.QuickGuideUserDetailsService;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebSecurity (debug = true)
//@ComponentScan(basePackages = "com.honsoft")
@EnableJpaRepositories("com.honsoft")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private QuickGuideUserDetailsService quickGuideUserDetailsService;
	
	//@Override
	//protected void configure(HttpSecurity http) throws Exception {
	//	// http.authorizeRequests()
	//}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password(bCryptPasswordEncoder().encode("pass")).roles("USER");
		
		//auth
        //.jdbcAuthentication()
        //.dataSource(dataSource())
        //.passwordEncoder(bCryptPasswordEncoder());
		
		//auth.userDetailsService(quickGuideUserDetailsService);
	}
	
	@Bean
	protected PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
        .ignoring()
           .antMatchers("/start.json"); // #3
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/**").permitAll();
	}
	
	@Bean
	public DataSource dataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/quickguide");
		ds.setUsername("shoppingnt");
		ds.setPassword("Shoppingnt2021!@");
		return ds;
	}
	
	
	 @Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.honsoft" });

	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());

	      return em;
	   }
	 
	 @Bean
	 public PlatformTransactionManager transactionManager() {
	     JpaTransactionManager transactionManager = new JpaTransactionManager();
	     transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

	     return transactionManager;
	 }

	 @Bean
		public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	     return new PersistenceExceptionTranslationPostProcessor();
	 }
    
	 
	 @Bean
	 public QuickGuideUserDetailsService quickGuideUserDetailsService() {
		 return new QuickGuideUserDetailsService();
	 }
    private Properties additionalProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.hbm2ddl.auto", "validation");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
	
}