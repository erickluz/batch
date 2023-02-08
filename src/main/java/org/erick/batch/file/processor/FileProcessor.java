package org.erick.batch.file.processor;

import org.erick.batch.domain.Transaction;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileProcessor {

	@Bean("processorFile")
	public FunctionItemProcessor<Transaction, String> processorFile() {
		return new FunctionItemProcessor<>(item -> item.toString());
	}
	
}
