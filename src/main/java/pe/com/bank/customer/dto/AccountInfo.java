package pe.com.bank.customer.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.bank.customer.client.entity.TransactionEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
	
	private String id;
	private String accountNumber;
	private Double amount;
	private Date dateOpen;
	private String amounttype;	
	private String productId;
	private List<TransactionEntity> transactionList;

}
