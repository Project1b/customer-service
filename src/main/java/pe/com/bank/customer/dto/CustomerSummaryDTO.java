package pe.com.bank.customer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSummaryDTO {
	
	private String customerId;
	private List<AccountInfo> accountList;
	private List<CreditInfo> creditList;
	private List<LoanInfo> loanList;	
	

}
