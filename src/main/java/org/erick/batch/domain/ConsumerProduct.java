package org.erick.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsumerProduct extends Product {
	private Double costValue;
}