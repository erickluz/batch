package org.erick.batch.file.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobFile {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job jobFlatFile(JobRepository jobRepository, @Qualifier("fileStep") Step step) {
		return jobBuilderFactory
				.get("jobFile")
				.repository(jobRepository)
				.start(step)
				.build();
	}

}
