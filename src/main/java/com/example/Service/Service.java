package com.example.demo;

@Service
@Slfj
public class Service {

@Autowired
AddressRepository address;

@Autowired
PriceRepository repoPrice;

@Autowired
CandidateRepository repoCandidate;

@Autowired
Utility util;

@Autowired
ServiceIntegrator intg;

/** method saveInterview 
* input -> InterviewDocument
* return Status 201 Created.
**/

Mono<ResponseEntity<HTTPStatus>> saveProductPrice(Price request, UUID productId){

return repoPrice.updatePrice(productID, request.getValue()).flatMap(res -> Mono.just(ResponseEntity<>(res, HttpStatus.OK)));

}


/** method getAllCandidates 
* input -> InterviewDocument
* return List<Candidate>.
**/

Mono<Product> getProduct(UUID productID){

return intg.getProductDetails(productID).flatMap(resp -> {
	
	return repoPrice.getPrice(productID).flatMap(price -> {
		//getCountry of store as per location id from Address
		return address.findById(resp.getStore().getLocationId())
				.flatMap(addr -> {
					//mapping currency code
			    price.setCurrency_code(util.getCurrencySymbol(addr.getCountry()));
			    resp.setCurrent_price(price);
			    return Mono.just(addr);
	})
	
	})
		return Mono.just(resp);	
});

}

/** method  getInterviewHours
* input -> InterviewDocument
* return Hours as String.
**/

Mono<String> getPanelHours(InterviewDocument request){
Integer hours  =0 ;
//getList of interview of each interview get the difference of starttime and end time and convert to hours
return repo.getAllCandidateByStatus(request.getStartDateTime(), request.getEndDateTime())

       .flatMap(interview -> {
       
       hours += util.getHours(interview.getStartDateTime(), interview.getgetEndDateTime());
       return Mono.just(interview);
       })
       .flatMap(obj -> Mono.just(hours.to String()));
    }

}
