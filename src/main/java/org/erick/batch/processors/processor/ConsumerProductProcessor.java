package org.erick.batch.processors.processor;

import org.erick.batch.domain.ConsumerProduct;
import org.springframework.batch.item.ItemProcessor;

public class ConsumerProductProcessor implements ItemProcessor<ConsumerProduct, ConsumerProduct> {

	@Override
	public ConsumerProduct process(ConsumerProduct consumerProduct) throws Exception {
		System.out.println("Processing consumer product" + consumerProduct.toString());
		return consumerProduct;
	}

}
