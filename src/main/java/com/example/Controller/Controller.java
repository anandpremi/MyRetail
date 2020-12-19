package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@RestController
@Slfj
public class Controller {

    @Autowired
    Service service;
    
    /** getAll candidate 
     * input ->  Product
     * ResponseEntity
     * */
    @PatchMapping(value = "/product", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_STREAM_JSON_VALUE})
	public Mono<ResponseEntity<HTTPStatus>> savePrice( @RequestParam UUID productId, @NotNull @RequestBody Price price) {
		
		log.info("Controller product request came for savePrice {}", price);
		
		return service.saveProductPrice(request, productId);
		
		}
		
	/** get product id 
     * input -> UUID
     * return Mono<Product>
    **/	
   @GetMapping(value = "/product", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_STREAM_JSON_VALUE})
	public Mono<Product> getProductDetails( @RequestParam UUID productId) {
		
		log.info("Controller interview request came for getProductDetails {}",productId);
		
		return service.getProduct(productId);
		
		}

}
