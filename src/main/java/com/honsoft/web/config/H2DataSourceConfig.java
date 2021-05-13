package com.honsoft.web.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

//@Configuration
//@MapperScan(basePackages = { "com.honsoft" }}
@MapperScan(value = {"com.honsoft.web.mapper.h2" }, sqlSessionFactoryRef = "h2SqlSessionFactory")
@EnableTransactionManagement
@PropertySource("classpath:/app.properties")
public class H2DataSourceConfig {

	@Autowired
	private Environment env;
	
	@Bean(name = "h2DataSource")
	@Primary
	public DataSource h2DataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(env.getProperty("datasource.h2.driverClassName"));
		ds.setJdbcUrl(env.getProperty("datasource.h2.jdbcUrl"));
		ds.setUsername("sa");
		ds.setPassword("sa");
		return ds;
	}

	@Bean(name = "h2SqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("h2DataSource") DataSource dataSource,
			ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/h2/mybatis-config.xml"));
		//factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/h2/mappers/*.xml"));
		return factoryBean.getObject();
	}

	@Bean(name = "h2SqlSession")
	public SqlSessionTemplate sqlSession(@Qualifier("h2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
