package org.erick.batch.file.processor;

import org.erick.batch.domain.CashierOperator;
import org.erick.batch.domain.Client;
import org.erick.batch.domain.Product;
import org.erick.batch.domain.Transaction;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileProcessor {

	@Bean("processorFileTransaction")
	public FunctionItemProcessor<Transaction, String> processorFileTransaction() {
		return new FunctionItemProcessor<>(item -> item.toString());
	}
	
	@Bean("processorFileClient")
	public FunctionItemProcessor<Client, String> processorFileClient() {
		return new FunctionItemProcessor<>(item -> item.toString());
	}
	
	@Bean("processorFileProduct")
	public FunctionItemProcessor<Product, String> processorFileProduct() {
		return new FunctionItemProcessor<>(item -> item.toString());
	}
	
	@Bean("processorFileCashier")
	public FunctionItemProcessor<Object, String> processorFileCashier() {
		return new FunctionItemProcessor<>(item -> item.toString());
	}
	
	@Bean("multiFileCashierProcessor")
	public FunctionItemProcessor<CashierOperator, String> processorMultiFileCashier() {
		return new FunctionItemProcessor<>(item -> item.toString());
	}
}
