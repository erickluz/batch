package org.erick.batch.file.writer;


import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileWriter {

	@Bean("writerFileTransaction")
	public ItemWriter<String> writeFileTransaction() {
		return itens -> itens.forEach(item -> System.out.println(item));
	}
	
	@Bean("writerFileClient")
	public ItemWriter<String> writeFileClient() {
		return itens -> itens.forEach(item -> System.out.println(item));
	}
	
	@Bean("writerFileProduct")
	public ItemWriter<String> writeFileProduct() {
		return itens -> itens.forEach(item -> System.out.println(item));
	}
	
	@Bean("writerFileCashier")
	public ItemWriter<String> writeFileCashier() {
		return itens -> itens.forEach(item -> System.out.println(item));
	}
	
	@Bean("multiFileCashierWriter")
	public ItemWriter<String> writeMultiFileCashier() {
		return itens -> itens.forEach(item -> System.out.println(item));
	}
}
