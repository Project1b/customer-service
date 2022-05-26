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
public class LoanInfo {
	
	
    private String loanId;
    private Double debt;
    private Date paymentDate;
    private Date endDate;
    private int quota;
    private String debtStatus;
    private int pendingQuota;
    private String productId;
    private List<TransactionEntity> transactionList;

}
