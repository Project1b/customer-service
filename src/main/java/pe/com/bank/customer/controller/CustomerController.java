package pe.com.bank.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.com.bank.customer.dto.CustomerAccountDTO;
import pe.com.bank.customer.entity.Customer;
import pe.com.bank.customer.service.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RefreshScope
@RestController
@RequestMapping("/v1")
public class CustomerController {

	
	   CustomerService customerService;
	    
	    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	    
	    @PostMapping("/customers")
	    public Mono<Customer> addCustomer(@RequestBody Customer customer){
	    	log.info("addCustomer");
	    	return customerService.addCustomer(customer);
	    }
	    
	    @GetMapping("/customers")
	    public Flux<Customer> getCustomers() {
	    	log.info("getCustomers");
	        return customerService.getCustomers();
	    }
	    
	    @GetMapping("/customers/{id}")
	    public Mono<Customer> getCustomerById(@PathVariable String id){
	    	log.info("getCustomerById");
	    	return customerService.getCustomerById(id);
	    }
	    
	    @PutMapping("/customers/{id}")
	    public Mono<Customer> updateCustomer(@RequestBody Customer customerUpdate, @PathVariable String id){
	    	return customerService.updateCustomer(customerUpdate, id);
	    }
	    	    
	    @DeleteMapping("/customers/{id}")
	    public Mono<Void> deleteCustomerById(@PathVariable String id){
	    	return customerService.deleteCustomerById(id);
	    }
	    
	    @GetMapping("/customers/accounts/{id}")
	    public Mono<CustomerAccountDTO> getCustomerAccountByCustomer(@PathVariable String id){
	    	return customerService.getCustomerAccountByCustomerId(id);
	    }
	    
	
}
