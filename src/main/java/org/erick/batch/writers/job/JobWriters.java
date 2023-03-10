package org.erick.batch.writers.job;

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
public class JobWriters {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job jobOperationsCashiersFile(JobRepository jobRepository, 
			@Qualifier("stepWriteOperationsCashiers") Step step) {
		return jobBuilderFactory
				.get("jobOperationsCashiersFile")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Job jobWriteDelimited(JobRepository jobRepository, @Qualifier("stepWriteDelimited") Step step) {
		return jobBuilderFactory
				.get("jobWriteDelimited")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Job jobWriterComplex(JobRepository jobRepository, @Qualifier("stepWriteComplex") Step step) {
		return jobBuilderFactory
				.get("jobWriterComplex")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Job jobWriterMultiFilesComplex(JobRepository jobRepository, @Qualifier("stepWriteMultiFilesComplex") Step step) {
		return jobBuilderFactory
				.get("jobWriterMultiFilesComplex")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Job jobWriterJDBC(JobRepository jobRepository, @Qualifier("stepWriteJDBC") Step step) {
		return jobBuilderFactory
				.get("jobWriterJDBC")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
}
