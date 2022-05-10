package pe.com.bank.customer.client.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	@Id
	private String id;
	private String accountNumber;
	private String amount;
	private String dateOpen;
	private String amounttype;	
	private String productId;
	private String customerId;
}
