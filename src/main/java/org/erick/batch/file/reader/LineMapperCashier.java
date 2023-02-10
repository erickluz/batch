package org.erick.batch.file.reader;

import java.util.HashMap;
import java.util.Map;

import org.erick.batch.domain.CashierOperator;
import org.erick.batch.domain.OperationsCashier;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LineMapperCashier {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean(name = "cashierLineMapper")
	public PatternMatchingCompositeLineMapper lineMapperCashier() {
		PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
		lineMapper.setTokenizers(tokenizers());
		lineMapper.setFieldSetMappers(fieldMappers());
		return lineMapper;
	}

	@SuppressWarnings("rawtypes")
	private Map<String, FieldSetMapper> fieldMappers() {
		Map<String, FieldSetMapper> fieldMappers = new HashMap<>();
		fieldMappers.put("0*", fieldSetMapper(CashierOperator.class));
		fieldMappers.put("1*", fieldSetMapper(OperationsCashier.class));
		return fieldMappers;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private FieldSetMapper fieldSetMapper(Class classe) {
		BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(classe);
		return fieldSetMapper;
	}

	private Map<String, LineTokenizer> tokenizers() {
		Map<String, LineTokenizer> tokenizers = new HashMap<>();
		tokenizers.put("0*", cashierTokenizer());
		tokenizers.put("1*", OperationsTokenizer());
		return tokenizers;
	}

	private LineTokenizer OperationsTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "operation", "value");
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setIncludedFields(1, 2, 3);
		return lineTokenizer;
	}

	private LineTokenizer cashierTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "name", "surname", "nsu", "age", "email");
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setIncludedFields(1, 2, 3, 4, 5, 6);
		return lineTokenizer;
	}
}
