package org.erick.batch.jdbc.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

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
import org.springframework.jdbc.core.RowMapper;

@Configuration
public class JdbcReader {

	@Bean
	public JdbcCursorItemReader<Client> clientJdbcCursorReader(
			@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Client>()
				.name("clientJdbcCursorReader")
				.dataSource(dataSource)
				.sql("SELECT * FROM CLIENT")
				.rowMapper(rowMapper())
				.build();
	}
	
	private RowMapper<Client> rowMapper() {
		return new RowMapper<Client>() {
			
			@Override
			public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
				if(rs.getString("name").equals("Erick")){
					throw new SQLException("name not allowed");
				} else {
					return clientRowMapper(rs);
				}
			}

			private Client clientRowMapper(ResultSet rs) throws SQLException {
				Client client = new Client();
				client.setId(rs.getLong("id"));
				client.setName(rs.getString("name"));
				client.setSurname(rs.getString("surname"));
				client.setCpf(rs.getString("cpf"));
				client.setPhoneNumber(rs.getString("phonenumber"));
				return client;
			}
		};
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
