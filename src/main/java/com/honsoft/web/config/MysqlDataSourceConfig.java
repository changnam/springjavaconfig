package com.honsoft.web.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;

import org.mybatis.spring.SqlSessionTemplate;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(value = { "com.honsoft.web.mapper.mysql" }, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
@EnableTransactionManagement
@PropertySource("classpath:/app.properties")
public class MysqlDataSourceConfig {
	@Autowired
	private Environment env;

	@Bean(name = "mysqlDataSource")
	public DataSource dataSource() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(env.getProperty("datasource.mysql.driver"));
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/quickguide");
		ds.setUsername("shoppingnt");
		ds.setPassword("Shoppingnt2021!@");
		return ds;
	}

	@Bean(name = "mysqlSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDataSource") DataSource dataSource,
			ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);

		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		factoryBean.setConfiguration(configuration);

		return factoryBean.getObject();
	}

	@Bean(name = "mysqlSqlSession")
	public SqlSessionTemplate sqlSession(@Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}