package org.erick.batch.processors.processor;

import org.erick.batch.domain.SaleProduct;
import org.springframework.batch.item.ItemProcessor;

public class SaleProductProcessor implements ItemProcessor<SaleProduct, SaleProduct> {

	@Override
	public SaleProduct process(SaleProduct saleProduct) throws Exception {
		System.out.println("Processing Sale product. " + saleProduct.toString());
		return saleProduct;
	}

}
