package org.erick.batch.domain;

import lombok.ToString;

@ToString(callSuper = true)
public class SaleProduct extends Product {
	private Double saleValue;
	private Double purchasePrice;
}