package pe.com.bank.customer.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import pe.com.bank.customer.client.entity.AccountEntity;
import pe.com.bank.customer.client.entity.CreditEntity;
import pe.com.bank.customer.client.entity.TransactionEntity;
import reactor.core.publisher.Flux;


@Component
public class TransactionRestClient {
	

	private WebClient webClient;		
	  
	  public TransactionRestClient(WebClient webClient) {
	        this.webClient = webClient;
	    }
	  
	  
	  @Value("${restClient.transactionUrl}")
	  private String transactionUrl;
	  	  	  
	  
	  public Flux<TransactionEntity> getTrasanctionByAccountId(String accountId){
		  var url = transactionUrl.concat("/v1/transactions/accountId/{id}");
		  
		  return  webClient
	                .get()
	                .uri(url,accountId)
	                .retrieve()
	                .bodyToFlux(TransactionEntity.class)
	                .log();
	  }
	  
	  
	  public Flux<TransactionEntity> getTrasanctionByCredittId(String creditId){
		  var url = transactionUrl.concat("/v1/transactions/creditId/{id}");
		  
		  return  webClient
	                .get()
	                .uri(url,creditId)
	                .retrieve()
	                .bodyToFlux(TransactionEntity.class)
	                .log();
	  }

}
