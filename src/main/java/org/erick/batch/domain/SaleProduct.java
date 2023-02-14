package org.erick.batch.domain;

import lombok.Data;
import lombok.ToString;

@ToString(callSuper = true)
@Data
public class SaleProduct extends Product {
	private Double saleValue;
	private Double purchasePrice;
}