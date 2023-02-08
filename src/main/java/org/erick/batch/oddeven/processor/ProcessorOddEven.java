package org.erick.batch.oddeven.processor;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorOddEven {
	
	@Bean
	public FunctionItemProcessor<Integer, String> oddEvenProcessor() {
		return new FunctionItemProcessor<Integer, String> (item -> 
			item % 2 == 0 ? String.format("O item %s é Par", item) : String.format("O item %s é Impar", item)
		); 
	}
}
