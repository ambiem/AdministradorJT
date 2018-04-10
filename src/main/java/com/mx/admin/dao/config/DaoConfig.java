package com.mx.admin.dao.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.mx.admin.dao.config")
@EnableTransactionManagement
public class DaoConfig {

	public DataSource dataSourceJNDI() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		try {
			dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			dataSource.setUsername("administrador_jt");
			dataSource.setPassword("administrador_jt");
		} catch (Exception e) {
			System.out.println("Error => " + e.getMessage());
			e.printStackTrace();
		}
		return dataSource;
	}

	@Bean
	public NamedParameterJdbcTemplate getNameParameterJbdcTemplate() {
		try {
			return new NamedParameterJdbcTemplate(dataSourceJNDI());
		} catch (Exception e) {
			System.out.println("Error => " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Bean
	public SimpleJdbcCall getSimpleJdbcCall() {
		SimpleJdbcCall simpleJdbcCall = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(dataSourceJNDI());
		} catch (Exception e) {
			System.out.println("Descripción => " + e.getMessage());
			e.printStackTrace();
		}
		return simpleJdbcCall;
	}
}
