package pe.com.bank.customer.client.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

	private String id;
	private String accountNumber;
	private Double amount;
	private Date dateOpen;
	private String amounttype;	
	private String productId;
	private String customerId;
}
