package org.erick.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	
	private Long id;
	private String paymentMethod;
	private String value;
	private String datetime;

}
