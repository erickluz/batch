package org.erick.batch.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerProduct extends Product {
	private Double costValue;
}