package org.erick.batch.jdbc.writer;


import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	
public class JdbcWriter {
	
	@Bean
	public ItemWriter<String> clientJdbcWriter() {
		return itens -> System.out.println(itens);
	}

}
