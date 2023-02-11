package org.erick.batch.readers.jdbc.processor;

import org.erick.batch.domain.Client;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcProcessor {
	

	@Bean
	public FunctionItemProcessor<Client, String> clientJdbcProcessor() {
		return new FunctionItemProcessor<Client, String>(item -> item.toString());
	}

}
