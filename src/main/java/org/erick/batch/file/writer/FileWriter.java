package org.erick.batch.file.writer;


import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileWriter {

	@Bean("writerFile")
	public ItemWriter<String> writeFile() {
		return itens -> itens.forEach(item -> System.out.println(item));
	}
}
