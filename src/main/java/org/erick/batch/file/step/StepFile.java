package org.erick.batch.file.step;


import org.erick.batch.domain.Client;
import org.erick.batch.domain.Product;
import org.erick.batch.domain.Transaction;
import org.erick.batch.file.reader.FileCashierReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepFile {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean("fileStepTransaction")
	public Step stepFileTransaction(@Qualifier("readFileTransaction") FlatFileItemReader<Transaction> reader,
								    @Qualifier("processorFileTransaction") FunctionItemProcessor<Transaction, String> processor,
								    @Qualifier("writerFileTransaction") ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("stepFileTransaction")
				.<Transaction , String> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@Bean("fileStepClient")
	public Step stepFileClient(@Qualifier("readFileClient") FlatFileItemReader<Client> reader,
						       @Qualifier("processorFileClient") FunctionItemProcessor<Client, String> processor,
						       @Qualifier("writerFileClient") ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("stepFileClient")
				.<Client , String> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@Bean("fileStepProducts")
	public Step stepFileProduct(@Qualifier("readFileProduct") FlatFileItemReader<Product> reader,
						        @Qualifier("processorFileProduct") FunctionItemProcessor<Product, String> processor,
						        @Qualifier("writerFileProduct") ItemWriter<String> writer) {
		return stepBuilderFactory
				.get("stepFileProduct")
				.<Product , String> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean("fileStepCashier")
	public Step stepFileCashier(@Qualifier("readFileCashier") FlatFileItemReader reader,
								@Qualifier("processorFileCashier") FunctionItemProcessor<Object, String> processor,
						        @Qualifier("writerFileCashier") ItemWriter writer) {
		return stepBuilderFactory
				.get("stepFileChashier")
				.chunk(1)
				.reader(new FileCashierReader(reader))
				.processor(processor)
				.writer(writer)
				.build();
	}
	
}
