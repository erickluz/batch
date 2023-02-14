package org.erick.batch.writers.processor;

import java.util.stream.Collectors;

import org.erick.batch.domain.CashierOperator;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessorWriters {

	@Bean
	public FunctionItemProcessor<CashierOperator, CashierOperator> processTransactionsCashierOperator() {
		return new FunctionItemProcessor<>(item -> {
			Double totalValue = item.getOperations().stream()
					.map(operation -> operation.getValue())
					.collect(Collectors.summingDouble(o -> o));
			item.setTotalTransaction(totalValue);
			return item;
		});
	}
}
