package org.erick.batch.readers.file.reader;

import java.util.HashMap;
import java.util.Map;

import org.erick.batch.domain.ConsumerProduct;
import org.erick.batch.domain.SaleProduct;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LineMapperProducts {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean(name = "productsLineMapper")
	public PatternMatchingCompositeLineMapper productsLineMapper() {
		PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
		lineMapper.setTokenizers(tokenizers());
		lineMapper.setFieldSetMappers(fieldMappers());
		return lineMapper;
	}

	@SuppressWarnings("rawtypes")
	private Map<String, FieldSetMapper> fieldMappers() {
		Map<String, FieldSetMapper> fieldMappers = new HashMap<>();
		fieldMappers.put("1*", fieldSetMapper(SaleProduct.class));
		fieldMappers.put("0*", fieldSetMapper(ConsumerProduct.class));
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
		tokenizers.put("1*", saleProductTokenizer());
		tokenizers.put("0*", consumerProductTokenizer());
		return tokenizers;
	}

	private LineTokenizer consumerProductTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "name", "costValue");
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setIncludedFields(1, 2, 3);
		return lineTokenizer;
	}

	private LineTokenizer saleProductTokenizer() {
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames("id", "name", "saleValue", "purchasePrice");
		lineTokenizer.setIncludedFields(1, 2, 3, 4);
		return lineTokenizer;
	}
}
