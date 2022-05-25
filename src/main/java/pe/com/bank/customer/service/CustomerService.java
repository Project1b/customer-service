package pe.com.bank.customer.service;

import pe.com.bank.customer.dto.CustomerAccountDTO;
import pe.com.bank.customer.dto.CustomerSummaryDTO;
import pe.com.bank.customer.entity.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
	
	 public Mono<Customer> addCustomer(Customer customer);
	 public Flux<Customer> getCustomers();
	 public Mono<Customer> getCustomerById(String id);
	 public Mono<Customer> updateCustomer(Customer customer, String id);
	 public Mono<Void> deleteCustomerById(String id);
	 public Mono<CustomerAccountDTO> getCustomerAccountByCustomerId(String id);
	 public Mono<CustomerSummaryDTO> getCustomerSummary(String customerId);
	 

}
