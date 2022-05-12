package pe.com.bank.customer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pe.com.bank.customer.client.AccountRestClient;
import pe.com.bank.customer.dto.CustomerAccountDTO;
import pe.com.bank.customer.entity.Customer;
import pe.com.bank.customer.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService{
	
	    AccountRestClient  accountRestClient;
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

			//test 
				
	// return customerAccountDTO;
}
		
		
}
		
