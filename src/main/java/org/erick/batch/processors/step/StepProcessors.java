package org.erick.batch.processors.step;


import org.erick.batch.domain.Client;
import org.erick.batch.domain.Product;
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
									   @Qualifier("compositeItemProcessor") ItemProcessor<Client, Client> processor,
									   @Qualifier("writerForProcessors") ItemWriter<Client> writer) {
		return stepBuilderFactory
				.get("stepProcessorValidator")
				.<Client, Client> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.faultTolerant()
				.skip(Exception.class)
				.skipLimit(3)
				.build();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step stepProcessorClassifier(@Qualifier("readFileProduct") FlatFileItemReader<Product> reader,
								        @Qualifier("processorClassifier") ItemProcessor processor,
								 	    @Qualifier("writerForProductProcessors") ItemWriter<Product> writer) {
		return stepBuilderFactory
				.get("stepProcessorClassifier")
				.<Product, Product> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.faultTolerant()
				.skip(Exception.class)
				.skipLimit(3)
				.build();
	}

}
