package org.erick.batch.jdbc.reader;

import javax.sql.DataSource;

import org.erick.batch.domain.Client;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class JdbcReader {

	@Bean
	public JdbcCursorItemReader<Client> clientJdbcReader(
			@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Client>()
				.name("jdbcCursorReader")
				.dataSource(dataSource)
				.sql("SELECT * FROM CLIENT")
				.rowMapper(new BeanPropertyRowMapper<Client>(Client.class))
				.build();
	}
	
}
