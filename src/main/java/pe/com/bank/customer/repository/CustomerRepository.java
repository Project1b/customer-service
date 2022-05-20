package pe.com.bank.customer.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bank.customer.entity.Customer;

@Repository
public interface CustomerRepository  extends ReactiveMongoRepository<Customer,String>{

	
	
	

}
