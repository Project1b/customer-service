package pe.com.bank.customer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import pe.com.bank.customer.client.entity.Account;
import reactor.core.publisher.Flux;

@Component
public class AccountRestClient {
	
	private WebClient webClient;		
	  
	  public AccountRestClient(WebClient webClient) {
	        this.webClient = webClient;
	    }
	  
	  
	  @Value("${restClient.accountUrl}")
	  private String accountUrl;
	  
	// @Value("${restClient.accountUrl}")
		 // private String clientServerName;
	  
	  
	  public Flux<Account> getAccountByCustomerId(String customerId){
		 // accountUrl ="http://account-service/v1/accounts/6275a7aab557542205eb1c1d";
		  var url = accountUrl.concat("/{id}");
		  
		  return  webClient
	                .get()
	                .uri(url,customerId)
	                .retrieve()
	                .bodyToFlux(Account.class)
	                .log();

	  }  

}
