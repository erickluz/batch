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
	public Job jobFlatFileTransaction(JobRepository jobRepository, @Qualifier("fileStepTransaction") Step step) {
		return jobBuilderFactory
				.get("jobFileTransaction")
				.repository(jobRepository)
				.start(step)
				.build();
	}
	
	@Bean
	public Job jobFileClients(JobRepository jobRepository, @Qualifier("fileStepClient") Step step) {
		return jobBuilderFactory
				.get("jobFileClient")
				.repository(jobRepository)
				.start(step)
				.build();
	}
	
	@Bean
	public Job jobFileProducts(JobRepository jobRepository, @Qualifier("fileStepProducts") Step step) {
		return jobBuilderFactory
				.get("jobFileProduct")
				.repository(jobRepository)
				.start(step)
				.build();
	}
	
	@Bean
	public Job jobFileCashier(JobRepository jobRepository, @Qualifier("fileStepCashier") Step step) {
		return jobBuilderFactory
				.get("jobFileCashier")
				.repository(jobRepository)
				.start(step)
				.build();
	}
	
	@Bean
	public Job jobFileMultiFile(JobRepository jobRepository, @Qualifier("fileStepMultiFile") Step step) {
		return jobBuilderFactory
				.get("jobFileMultiFile")
				.repository(jobRepository)
				.start(step)
				.build();
	}
}
