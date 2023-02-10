package org.erick.batch.oddeven.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobOddEven {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job jobEvenOdd(JobRepository jobRepository, @Qualifier("oddEvenStep") Step step) {
		return jobBuilderFactory
				.get("jobEvenOdd")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
