package org.erick.batch.file.step;


import org.erick.batch.domain.Transaction;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepFile {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean("fileStep")
	public Step stepFileFlat(@Qualifier("readFile") FlatFileItemReader<Transaction> reader,
							 @Qualifier("processorFile") FunctionItemProcessor<Transaction, String> processor,
							 @Qualifier("writerFile") ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("stepFileFlat")
				.<Transaction , String> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
}
