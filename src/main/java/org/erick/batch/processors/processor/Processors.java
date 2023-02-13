package org.erick.batch.processors.processor;

import org.erick.batch.domain.Client;
import org.erick.batch.domain.ConsumerProduct;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Processors {
	
	private static final int LIMIT_AGE = 100;
	
	@Bean
	public ItemProcessor<Client, Client> compositeItemProcessor() throws Exception {
		return new CompositeItemProcessorBuilder<Client, Client>()
				.delegates(processorValidadorBean(), processorValidadorValidating())
				.build();
	}
	
	private ItemProcessor<Client, Client> processorValidadorBean() throws Exception {
		BeanValidatingItemProcessor<Client> processor = new BeanValidatingItemProcessor<>();
		processor.setFilter(true);
		processor.afterPropertiesSet();
		return processor;
	}
	
	private ItemProcessor<Client, Client> processorValidadorValidating() {
		ValidatingItemProcessor<Client> processor = new ValidatingItemProcessor<>();
		processor.setValidator(validator());
		processor.setFilter(true);
		return processor;
	}

	private Validator<? super Client> validator() {
		return new Validator<Client>() {
			@Override
			public void validate(Client client) throws ValidationException {
				if (client.getAge().compareTo(LIMIT_AGE) > 0) {
					throw new ValidationException("Invalid age");
				}
			}
		};
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Bean
	public ItemProcessor processorClassifier() {
		return new ClassifierCompositeItemProcessorBuilder<>()
				.classifier(classifier())
				.build();
	}

	@SuppressWarnings({ "serial", "rawtypes" })
	private Classifier classifier() {
		return new Classifier<Object, ItemProcessor>() {

			@Override
			public ItemProcessor classify(Object classifiable) {
				if (classifiable instanceof ConsumerProduct) {
					return new ConsumerProductProcessor();
				} else {
					return new SaleProductProcessor();
				}
			}
			
		};
	}
	
	
	
}
