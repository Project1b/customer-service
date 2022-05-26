package pe.com.bank.customer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pe.com.bank.customer.client.AccountRestClient;
import pe.com.bank.customer.client.CreditRestClient;
import pe.com.bank.customer.client.LoanRestClient;
import pe.com.bank.customer.client.TransactionRestClient;
import pe.com.bank.customer.dto.AccountInfo;
import pe.com.bank.customer.dto.CreditInfo;
import pe.com.bank.customer.dto.CustomerAccountDTO;
import pe.com.bank.customer.dto.CustomerSummaryDTO;
import pe.com.bank.customer.dto.LoanInfo;
import pe.com.bank.customer.entity.Customer;
import pe.com.bank.customer.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{
	
	    AccountRestClient  accountRestClient;
	    CreditRestClient  creditRestClient;
	    LoanRestClient    loanRestClient;
	    TransactionRestClient  transactionRestClient;
	    CustomerRepository customerRepository;
	    
	    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	    public Mono<Customer> addCustomer(Customer customer){
	    	log.info("addCustomer");    	
	    	return customerRepository.save(customer).doOnNext(customerSaved -> log.info("Customer id :"+customer.getId()+" Saved"));
	    }
	    
	    public Flux<Customer> getCustomers(){
	    	log.info("getCustomers");  
	       return customerRepository.findAll().doOnNext(customer -> log.info("Customer id :"+customer.getId()));
	    }
	    
	    public Mono<Customer> getCustomerById(String id){
	    	log.info("getCustomerById");  
	    	return customerRepository.findById(id).doOnNext(customer -> log.info("Customer id :"+customer.getId()));
	    }
	    
	    public Mono<Customer> updateCustomer(Customer customerUpdate, String id){
	    	log.info("updateCustomer"); 
	    	return customerRepository.findById(id).flatMap(customer -> {
	    			
	    		customer.setCustomerType(customerUpdate.getCustomerType() != null ? customerUpdate.getCustomerType():customer.getCustomerType());
	    		customer.setDateAssociated(customerUpdate.getDateAssociated()!= null ? customerUpdate.getDateAssociated():customer.getDateAssociated());
 		
	    		return customerRepository.save(customer);
	 
	    	}).doOnNext(person -> log.info("Customer id :"+person.getId()+" Updated"));
	    }
	    
	    public Mono<Void> deleteCustomerById(String id){
	    	log.info("deleteCustomerById"); 
	    	return customerRepository.deleteById(id);
	    }

		public Mono<CustomerAccountDTO> getCustomerAccountByCustomerId(String id) {
						
			return customerRepository.findById(id).flatMap( customer -> { 
				return accountRestClient.getAccountByCustomerId(customer.getId()).collectList().map(a -> 
				new CustomerAccountDTO(customer.getId(),customer.getCustomerType(),customer.getDateAssociated(),a));									
				});

				
		}
		
		public Mono<CustomerSummaryDTO> getCustomerSummary(String customerId){
			
		var accounts = 	accountRestClient.getAccountByCustomerId(customerId).flatMap( account -> {
			
			 var transactions = transactionRestClient.getTrasanctionByAccountId(account.getId()).collectList();
			  
			return transactions.map( t -> new AccountInfo(account.getId(),account.getAccountNumber(),account.getAmount(),
					account.getDateOpen(),account.getAmounttype(),account.getProductId(),t));
		}).collectList();
		
		
		var credits = 	creditRestClient.getCreditByCustomerId(customerId).flatMap( credit -> {
			
			 var transactions = transactionRestClient.getTrasanctionByCredittId(credit.getCreditId()).collectList();
			  
			return transactions.map(t -> new CreditInfo(credit.getCreditId(),credit.getAmountUsed(),credit.getLimitCredit(),
					credit.getCreditAvailable(),credit.getNumberCredit(),credit.getType(),credit.getProductId(),t));
		}).collectList();
		
		loanRestClient.getLoanByCustomerId(customerId);
		var loans = 	loanRestClient.getLoanByCustomerId(customerId).flatMap( loan -> {
			
			 var transactions = transactionRestClient.getTrasanctionByAccountId(loan.getLoanId()).collectList();
			  
			return transactions.map(t -> new LoanInfo(loan.getLoanId(),loan.getDebt(),loan.getPaymentDate(),loan.getEndDate(),
					loan.getQuota(),loan.getDebtStatus(),loan.getPendingQuota(),loan.getProductId(),t));
		}).collectList();
		
			
			return accounts.flatMap( a -> {
				return credits.flatMap( c -> {
					return loans.map(  l -> new CustomerSummaryDTO(customerId,a,c,l));
				});
			});
			
		}
		
		
		
}
		
