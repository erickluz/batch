package org.erick.batch.processors.writer;

import org.erick.batch.domain.Client;
import org.erick.batch.domain.Product;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterProcessor {

	@Bean
	public ItemWriter<Client> writerForProcessors() {
		return itens -> itens.forEach(item -> System.out.println(item.toString()));
	}
	
	@Bean
	public ItemWriter<Product> writerForProductProcessors() {
		return itens -> itens.forEach(item -> System.out.println(item.toString()));
	}
}
