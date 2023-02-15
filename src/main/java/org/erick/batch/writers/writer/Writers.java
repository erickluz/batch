package org.erick.batch.writers.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.erick.batch.domain.CashierOperator;
import org.erick.batch.domain.Client;
import org.erick.batch.domain.OperationsCashier;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class Writers {

	@StepScope	
	@Bean
	public FlatFileItemWriter<CashierOperator> writerOperationsCashierFile(
			@Value("#{jobParameters['fileOutput']}") Resource output){
		return new FlatFileItemWriterBuilder<CashierOperator>()
				.name("writerOperationsCashierFile")
				.resource(output)
				.formatted()
				.format("%5s %8s")
				.names(new String[] {"name", "totalTransaction"})
				.build();
	}
	
	@StepScope
	@Bean
	public FlatFileItemWriter<Client> writerDelimited(
			@Value("#{jobParameters['fileOutputDelimited']}") Resource output) {
		return new FlatFileItemWriterBuilder<Client>()
				.name("writerDelimited")
				.resource(output)
				.delimited()
				.names(new String[] {"id", "name", "surname"})
				.build();
	}
	
	@StepScope
	@Bean
	public FlatFileItemWriter<CashierOperator> writerComplex(
			@Value("#{jobParameters['fileOutputComplex']}") Resource output,
			WriterFooterComplex footer) {
		return new FlatFileItemWriterBuilder<CashierOperator>()
				.name("writerComplex")
				.resource(output)
				.lineAggregator(lineAgregator())
				.footerCallback(footer)
				.headerCallback(header())
				.build();
	}

	private FlatFileHeaderCallback header() {
		return new FlatFileHeaderCallback() {
			
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.append("\t\t\tSYSTEM XPTO");
				writer.append("\n---------------------------------------");
				writer.append("\n\tDATE FILE: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
				writer.append("\n---------------------------------------");
			}
		};
	}

	private LineAggregator<CashierOperator> lineAgregator() {
		return new LineAggregator<CashierOperator>() {
			
			@Override
			public String aggregate(CashierOperator cashierOperator) {
				StringBuilder line = new StringBuilder();
				line.append("\nID: " + cashierOperator.getId() + " NAME " + cashierOperator.getName());
				line.append("\nTRANSACTIONS");
				for (OperationsCashier transaction : cashierOperator.getOperations()) {					
					line.append("\n	- ID: " + transaction.getId() + " VALUE: " + transaction.getValue());
				}
				return line.toString();
			}
		};
	}
	
}
