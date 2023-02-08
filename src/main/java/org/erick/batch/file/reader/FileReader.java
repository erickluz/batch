package org.erick.batch.file.reader;


import org.erick.batch.domain.Transaction;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class FileReader {

	@StepScope
	@Bean("readFile")
	public FlatFileItemReader<Transaction> readFlatFile(@Value("#{jobParameters['transactionFile']}") Resource resource) {
		return new FlatFileItemReaderBuilder<Transaction>()
				.name("readFileTransaction")
				.resource(resource)
				.fixedLength()
				.columns(new Range[] {new Range(1, 12), new Range(13, 33), new Range(34, 44), new Range(45, 54)})
				.names(new String[] {"id", "paymentMethod", "value", "datetime"})
				.targetType(Transaction.class)
				.build();
	}
}
