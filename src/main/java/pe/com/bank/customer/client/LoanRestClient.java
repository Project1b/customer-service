package pe.com.bank.customer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import pe.com.bank.customer.client.entity.LoanEntity;
import reactor.core.publisher.Flux;


@Component
public class LoanRestClient {
	
	private WebClient webClient;		
	  
	  public LoanRestClient(WebClient webClient) {
	        this.webClient = webClient;
	    }
	  
	  
	  @Value("${restClient.loanUrl}")
	  private String loanUrl;
	  
	  
	  public Flux<LoanEntity> getLoanByProductId(String productId){
		  var url = loanUrl.concat("/v1/getLoanByProductId/{productId");
		  
		  return  webClient
	                .get()
	                .uri(url,productId)
	                .retrieve()
	                .bodyToFlux(LoanEntity.class)
	                .log();

	  }  
	  
	  
	  public Flux<LoanEntity> getLoanByCustomerId(String customerId){
		  var url = loanUrl.concat("/v1//getLoanBycustomerId/{customerId}");
		  
		  return  webClient
	                .get()
	                .uri(url,customerId)
	                .retrieve()
	                .bodyToFlux(LoanEntity.class)
	                .log();

	  }  
	  


}
