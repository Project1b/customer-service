package pe.com.bank.customer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.bank.customer.client.entity.AccountEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccountDTO {
	
	private String id;
	private String customerType;
	private String dateAssociated;	
	private List<AccountEntity> accounts;
	
	

}
