package org.erick.batch.oddeven.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepOddEven {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean("oddEvenStep")
	public Step stepEvenOdd(@Qualifier("readerEvenOdd") IteratorItemReader<Integer> reader, 
							@Qualifier("processorEvenOdd") FunctionItemProcessor<Integer, String> processor, 
							@Qualifier("oddEvenWriter") ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("stepOddEven")
				.<Integer, String> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}
