package org.erick.batch.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OperationsCashier {
	private Long id;
	private String operation;
	private Double value;
}
