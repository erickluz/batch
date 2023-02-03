package org.erick.batch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobChunkOddEven {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job jobOddEven(JobRepository jobRepository) {
		return jobBuilderFactory.get("jobOddEven")
				.repository(jobRepository)
				.start(stepOddEven())
				.build();
	}

	private Step stepOddEven() {
		return stepBuilderFactory.get("stepOddEven")
				.<Integer, String> chunk(1)
				.reader(readerOddEven())
				.processor(oddEvenProcessor())
				.writer(oddEvenWriter())
				.build();
	}

	private IteratorItemReader<Integer> readerOddEven() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<>(numbers);
	}
	
	private FunctionItemProcessor<Integer, String> oddEvenProcessor() {
		return new FunctionItemProcessor<Integer, String> (item -> 
			item % 2 == 0 ? String.format("O item %s é Par", item) : String.format("O item %s é Impar", item)
		); 
	}
	
	private ItemWriter<String> oddEvenWriter() {
		return itens -> itens.forEach(item -> System.out.println(item));
	}
	
	
	
}
