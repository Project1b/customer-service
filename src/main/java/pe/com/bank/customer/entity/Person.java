package pe.com.bank.customer.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String documentType;
	private String documentNumber;
	private String phoneNumber;
	private String address;
	private String email;
	private String customerId;
	

}
 