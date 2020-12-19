package com.example.demo;


public class ServiceIntegrator{
	

public Product getProductDetails(UUID productId){
	
	@Autowired
	Connection productConnectionConfig;
	
	return productConnectionConfig.getWebClient().get().uri("https://api.target.com/products/v3/13860428?fields="
			+ "descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz"+productId)
			.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_STREAM_JSON)
			.retrieve()
			.bodyToMono(Product.class);
	
	}

}

