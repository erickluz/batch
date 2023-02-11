package org.erick.batch.processors.processor;

import org.erick.batch.domain.Client;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Processors {
	
	@Bean
	public ItemProcessor<Client, Client> processorValidadorBean() {
		BeanValidatingItemProcessor<Client> processor = new BeanValidatingItemProcessor<>();
		processor.setFilter(true);
		return processor;
	}
	
	@Bean
	public ItemProcessor<Client, Client> processorValidadorValidating() {
		ValidatingItemProcessor<Client> processor = new ValidatingItemProcessor<>();
		processor.setValidator(validator());
		processor.setFilter(true);
		return processor;
	}

	private Validator<? super Client> validator() {
		return new Validator<Client>() {
			@Override
			public void validate(Client client) throws ValidationException {
				if (client.getAge().compareTo(100) > 0) {
					throw new ValidationException("Invalid age");
				}
			}
		};
	}
	
	
	
}
