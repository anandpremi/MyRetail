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
	
//--------------- ** Activity for Saving file data to DB and Fetch ** -------------------------------
 
/** Saving file data to Mongo
 * input -> xls file
 * return ResponseEntity
 **/	
@PostMapping(value = "/upload", headers = ("content-type=multipart/*"), 
			consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public ResponseEntity<HttpStatus> uploadFile(@RequestHeader final HttpHeaders httpHeader,
			@RequestParam("file") MultipartFile file) {
	final String traceId = httpHeader.getFirst(SubscriptionsMerchantConstant.TRACE_ID);
	log.info("For Trace ID {} upload request has been received", traceId);
	String response = service.saveUser(file, traceId)
	return response.equals("Success") ? new ResponseEntity<>(HttpStatus.OK): new ResponseEntity<>(HttpStatus.badRequest());
	
	}

}
