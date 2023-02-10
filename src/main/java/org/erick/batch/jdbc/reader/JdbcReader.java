package org.erick.batch.jdbc.reader;

import javax.sql.DataSource;

import org.erick.batch.domain.Client;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class JdbcReader {

	@Bean
	public JdbcCursorItemReader<Client> clientJdbcCursorReader(
			@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Client>()
				.name("clientJdbcCursorReader")
				.dataSource(dataSource)
				.sql("SELECT * FROM CLIENT")
				.rowMapper(new BeanPropertyRowMapper<Client>(Client.class))
				.build();
	}
	
	@Bean
	public JdbcPagingItemReader<Client> clientJdbcPagingReader(
			@Qualifier("appDataSource") DataSource dataSource,
			PagingQueryProvider queryProvider) {
		return new JdbcPagingItemReaderBuilder<Client>()
				.name("clientJdbcPagingReader")
				.dataSource(dataSource)
				.queryProvider(queryProvider)
				.pageSize(1)
				.rowMapper(new BeanPropertyRowMapper<Client>(Client.class))
				.build();
	}
	
	@Bean
	public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier("appDataSource") DataSource dataSource) {
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setDataSource(dataSource);
		queryProvider.setSelectClause("SELECT *");
		queryProvider.setFromClause("FROM CLIENT");
		queryProvider.setSortKey("id");
		return queryProvider;
	}
	
}
