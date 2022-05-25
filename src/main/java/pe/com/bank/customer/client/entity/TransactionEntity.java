package pe.com.bank.customer.client.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionEntity {

	private String transactionId;
	private Double amount;
	private Double commission;
	private Date date;
	private String type;
	private String typeDetails;
	private String accountId;
	private Double commissionTr;
	private String creditId;
}
