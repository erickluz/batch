package org.erick.batch.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CashierOperator {
	private Long id;
	private String name;
	private String surname;
	private String nsu;
	private Integer age;
	private String email;
	private List<OperationsCashier> operations = new ArrayList<>();
}
