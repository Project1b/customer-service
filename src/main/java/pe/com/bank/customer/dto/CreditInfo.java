package pe.com.bank.customer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.bank.customer.client.entity.TransactionEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditInfo {
	
	private String creditId;
    private Double amountUsed;
    private Double limitCredit;
    private Double creditAvailable;
    private String numberCredit;
    private String type;
    private String productId;
	private List<TransactionEntity> transactionList;

}
