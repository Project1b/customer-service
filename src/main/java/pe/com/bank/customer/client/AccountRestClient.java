package pe.com.bank.customer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

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
	  
	  public Flux<Account> getAccountByCustomerId(String customerId){
		  
		  var url = UriComponentsBuilder.fromHttpUrl(accountUrl)
	                .queryParam("customerId", customerId)
	                .buildAndExpand().toUriString();
		  
		  return  webClient
	                .get()
	                .uri(url)
	                .retrieve()
	                .bodyToFlux(Account.class)
	                .log();

	  }  

}
