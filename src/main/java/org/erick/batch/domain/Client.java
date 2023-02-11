package org.erick.batch.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
	@NotNull
	private Long id;
	private String name;
	private String surname;
	@NotNull
	private Integer age;
	private String cpf;
	private String address;
	private String phoneNumber;
	@Email(message = "Invalid email format")
	private String email;
}
