package org.erick.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
	private Long id;
	private String name;
	private String surname;
	private Integer age;
	private String cpf;
	private String address;
	private String phoneNumber;
	private String email;
}
