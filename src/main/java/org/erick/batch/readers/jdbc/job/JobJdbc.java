package org.erick.batch.readers.jdbc.job;

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
public class JobJdbc {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job jobJDBCClients(JobRepository jobRepository, @Qualifier("clientCursorJdbcStep") Step step) {
		return jobBuilderFactory
				.get("jobCursorJDBCClients")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
	@Bean
	public Job jobPagingJDBCClients(JobRepository jobRepository, @Qualifier("clientPagingJdbcStep") Step step) {
		return jobBuilderFactory
				.get("jobPagingJDBCClients")
				.repository(jobRepository)
				.start(step)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
}
