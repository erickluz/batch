package org.erick.batch.jdbc.step;


import org.erick.batch.domain.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
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
	public Step clientJdbcStep(@Qualifier("clientJdbcReader") JdbcCursorItemReader<Client> reader,
							   @Qualifier("clientJdbcProcessor") FunctionItemProcessor<Client, String> processor,
							   @Qualifier("clientJdbcWriter") ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("clientJdbcStep")
				.<Client, String> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

}
