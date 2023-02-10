package org.erick.batch.jdbc.step;


import org.erick.batch.domain.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepJdbc {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step clientCursorJdbcStep(@Qualifier("clientJdbcCursorReader") JdbcCursorItemReader<Client> reader,
									 @Qualifier("clientJdbcProcessor") FunctionItemProcessor<Client, String> processor,
									 @Qualifier("clientJdbcWriter") ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("clientCursorJdbcStep")
				.<Client, String> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.faultTolerant()
				.skip(Exception.class)
				.skipLimit(2)
				.build();
	}
	
	@Bean
	public Step clientPagingJdbcStep(@Qualifier("clientJdbcPagingReader") JdbcPagingItemReader<Client> reader,
									 @Qualifier("clientJdbcProcessor") FunctionItemProcessor<Client, String> processor,
									 @Qualifier("clientJdbcWriter") ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("clientPagingJdbcStep")
				.<Client, String> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

}
