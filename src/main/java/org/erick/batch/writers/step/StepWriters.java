package org.erick.batch.writers.step;

import org.erick.batch.domain.CashierOperator;
import org.erick.batch.domain.Client;
import org.erick.batch.writers.writer.WriterFooterComplex;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.MultiResourceItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepWriters {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step stepWriteOperationsCashiers(@Qualifier("multiFileCashierReader") MultiResourceItemReader reader,
											@Qualifier("processTransactionsCashierOperator") FunctionItemProcessor<CashierOperator, CashierOperator> processor,
										    @Qualifier("writerOperationsCashierFile") FlatFileItemWriter<CashierOperator> writer) {
		return stepBuilderFactory
				.get("stepWriteOperationsCashiers")
				.<CashierOperator, CashierOperator> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
	
	@Bean("stepWriteDelimited")
	public Step stepWriteDelimited(@Qualifier("readFileClient") FlatFileItemReader<Client> reader,
								   @Qualifier("writerDelimited") FlatFileItemWriter<Client> writer) {
		return stepBuilderFactory
				.get("stepWriteDelimited")
				.<Client , Client> chunk(1)
				.reader(reader)
				.writer(writer)
				.build();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step stepWriteComplex(@Qualifier("multiFileCashierReader") MultiResourceItemReader reader,
								 @Qualifier("processTransactionsCashierOperator") FunctionItemProcessor<CashierOperator, CashierOperator> processor,
							     @Qualifier("writerComplex") FlatFileItemWriter<CashierOperator> writer,
							     WriterFooterComplex listener) {
		return stepBuilderFactory
				.get("stepWriteComplex")
				.<CashierOperator, CashierOperator> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.listener(listener)
				.build();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step stepWriteMultiFilesComplex(@Qualifier("multiFileCashierReader") MultiResourceItemReader reader,
								 @Qualifier("processTransactionsCashierOperator") FunctionItemProcessor<CashierOperator, CashierOperator> processor,
							     @Qualifier("writerMultiFileComplex") MultiResourceItemWriter<CashierOperator> writer,
							     WriterFooterComplex listener) {
		return stepBuilderFactory
				.get("stepWriteMultiFilesComplex")
				.<CashierOperator, CashierOperator> chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.listener(listener)
				.build();
	}
	
}
