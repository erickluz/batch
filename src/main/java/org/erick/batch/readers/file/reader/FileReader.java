package org.erick.batch.readers.file.reader;


import org.erick.batch.domain.CashierOperator;
import org.erick.batch.domain.Client;
import org.erick.batch.domain.Product;
import org.erick.batch.domain.Transaction;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class FileReader {

	@StepScope
	@Bean("readFileTransaction")
	public FlatFileItemReader<Transaction> readFileTransaction(@Value("#{jobParameters['transactionFile']}") Resource resource) {
		return new FlatFileItemReaderBuilder<Transaction>()
				.name("readFileTransaction")
				.resource(resource)
				.fixedLength()
				.columns(new Range[] {new Range(1, 12), new Range(13, 33), new Range(34, 44), new Range(45, 54)})
				.names(new String[] {"id", "paymentMethod", "value", "datetime"})
				.targetType(Transaction.class)
				.build();
	}
	
	@StepScope
	@Bean("readFileClient")
	public FlatFileItemReader<Client> readClientsFile(@Value("#{jobParameters['clientsFile']}") Resource resource) {
		return new FlatFileItemReaderBuilder<Client>()
				.name("readFileClient")
				.resource(resource)
				.delimited()
				.delimiter(";")
				.names(new String[] {"id", "name", "surname", "cpf", "address", "phoneNumber"})
				.targetType(Client.class)
				.build();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	@StepScope
	@Bean("readFileProduct")
	public FlatFileItemReader<Product> readProductsFile(@Value("#{jobParameters['productsFile']}") Resource resource,
			@Qualifier("productsLineMapper") LineMapper lineMapper) {
		return new FlatFileItemReaderBuilder<Product>()
				.name("readFileProduct")
				.resource(resource)
				.lineMapper(lineMapper)
				.build();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	@StepScope
	@Bean("readFileCashier")
	public FlatFileItemReader<CashierOperator> readCashierFile(@Value("#{jobParameters['cashierFile']}") Resource resource,
			@Qualifier("cashierLineMapper") LineMapper lineMapper) {
		return new FlatFileItemReaderBuilder<CashierOperator>()
				.name("readFileCashier")
				.resource(resource)
				.lineMapper(lineMapper)
				.build();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	@StepScope
	@Bean("multiFileCashierReader")
	public MultiResourceItemReader fileMultiCashierReader(@Value("#{jobParameters['multiCashierFile']}") Resource[] resource,
			@Qualifier("readFileCashier") FlatFileItemReader multiFileReader) {
		return new MultiResourceItemReaderBuilder<>()
				.name("fileMultiCashierReader")
				.resources(resource)
				.delegate(new FileMultiCashierReader(multiFileReader))
				.build();
	}
}
