package org.erick.batch.processors.readers;

import org.erick.batch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ReadersProcessors {

	@StepScope
	@Bean
	public FlatFileItemReader<Client> readerClientsProcessors(@Value("#{jobParameters['clientsProcessors']}") Resource resource) {
		return new FlatFileItemReaderBuilder<Client>()
				.name("readerClientsProcessors")
				.resource(resource)
				.delimited()
				.delimiter(",")
				.names(new String[] {"id", "name", "surname", "age", "cpf", "address", "phoneNumber", "email"})
				.targetType(Client.class)
				.build();
	}

}
