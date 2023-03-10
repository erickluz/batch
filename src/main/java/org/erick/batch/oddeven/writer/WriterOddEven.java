package org.erick.batch.oddeven.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterOddEven {

	@Bean("oddEvenWriter")
	public ItemWriter<String> oddEvenWriter() {
		return itens -> itens.forEach(item -> System.out.println(item));
	}
}
