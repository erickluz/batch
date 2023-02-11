package org.erick.batch.processors.step;


import org.erick.batch.domain.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepProcessors {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step stepProcessorValidator(@Qualifier("readerClientsProcessors") FlatFileItemReader<Client> reader,
									   @Qualifier("processorValidadorBean") ItemProcessor<Client, Client> processor1,
									   @Qualifier("processorValidadorValidating") ItemProcessor<Client, Client> processor2,
									   @Qualifier("writerForProcessors") ItemWriter<Client> writer) {
		return stepBuilderFactory
				.get("stepProcessorValidator")
				.<Client, Client> chunk(1)
				.reader(reader)
				.processor(processor1)
				.processor(processor2)
				.writer(writer)
				.faultTolerant()
				.skip(Exception.class)
				.skipLimit(2)
				.build();
		
	}

}
