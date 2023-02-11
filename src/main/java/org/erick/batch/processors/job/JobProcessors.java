package org.erick.batch.processors.job;

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
public class JobProcessors {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job jobProcessorValidator(JobRepository jobRepository, @Qualifier("stepProcessorValidator") Step step) {
		return jobBuilderFactory
				.get("jobProcessorValidator")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
