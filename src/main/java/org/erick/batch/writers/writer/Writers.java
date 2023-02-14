package org.erick.batch.writers.writer;

import org.erick.batch.domain.CashierOperator;
import org.erick.batch.domain.Client;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class Writers {

	@StepScope	
	@Bean
	public FlatFileItemWriter<CashierOperator> writerOperationsCashierFile(
			@Value("#{jobParameters['fileOutput']}") Resource output){
		return new FlatFileItemWriterBuilder<CashierOperator>()
				.name("writerOperationsCashierFile")
				.resource(output)
				.formatted()
				.format("%5s %8s")
				.names(new String[] {"name", "totalTransaction"})
				.build();
	}
	
	@StepScope
	@Bean
	public FlatFileItemWriter<Client> writerDelimited(
			@Value("#{jobParameters['fileOutputDelimited']}") Resource output) {
		return new FlatFileItemWriterBuilder<Client>()
				.name("writerDelimited")
				.resource(output)
				.delimited()
				.names(new String[] {"id", "name", "surname"})
				.build();
	}
	
}
