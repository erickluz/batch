package org.erick.batch.oddeven.reader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderOddEven {
	
	@StepScope
	@Bean("readerEvenOdd")
	public IteratorItemReader<Integer> readerEvenOdd() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<>(numbers);
	}
}
