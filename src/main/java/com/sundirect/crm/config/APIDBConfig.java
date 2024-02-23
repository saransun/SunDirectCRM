package com.sundirect.crm.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "APIEntityManagerFactory", 
		basePackages = {
				"com.sundirect.crm.apirepo"
		}
)
public class APIDBConfig {

	@Bean(name = "APIDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "APIEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean apiEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("APIDataSource") DataSource dataSource) {
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");
		
		return builder.dataSource(dataSource)
					  .properties(properties)
					  .packages("com.sundirect.crm.apientity")
					  .persistenceUnit("API")
					  .build();
	}

	@Bean(name = "APITransactionManager")
	public PlatformTransactionManager apiTransactionManager(@Qualifier("APIEntityManagerFactory") EntityManagerFactory apiEntityManagerFactory) {
		return new JpaTransactionManager(apiEntityManagerFactory);
	}
}