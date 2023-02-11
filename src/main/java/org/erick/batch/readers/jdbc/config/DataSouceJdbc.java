package org.erick.batch.readers.jdbc.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration	
public class DataSouceJdbc {

	@Primary
	@Bean("springBatchDataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource springBatchDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("appDataSource")
	@ConfigurationProperties(prefix = "app.datasource")
	public DataSource appDataSource() {
		return DataSourceBuilder.create().build();
	}
}
