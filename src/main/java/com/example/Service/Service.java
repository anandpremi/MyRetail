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
EmployeeMongoRecord mongoRepo;

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


/** method getProduct 
* input -> UUID
* return Mono<Product>.
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

// ------------------------ ** File save activity starts here ** ---------------------------
	
public String saveUser(MultipartFile file, String traceId) {
		log.info("Inside saveUser method for traceId :: {}", traceId);

		List<Employee> list = new ArrayList<Employee>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = sheet.getRow(i);
				
			Employee user = Employee.builder().id(UUID.randomUUID().toString())
					name(row.getCell(1).getStringCellValue()).age(row.getCell(2).getNumericCellValue())
					.build();
					list.add(user);
				
			//condition to save every 10k record and empty the list
			if(list.size>10000){
			mongoRepo.saveAll(list);
			list = Collections.emptyList();
			}
			
				
			}
			workbook.close();
		} catch (IOException e) {
			log.error("For traceId{} error occuring during saveUser :: {}", traceId, e);
			return "Failure";
		}
		return "Success";
	}	
	
	
	
public Employee getEmployee(UUID id){
 
   return mongoRepo.findById(id);

}	

		
public Employee deleteEmployee(UUID id){
 
   return mongoRepo.deleteById(id);

}
	
public Employee updateEmployee(Employee emp){
 
   return mongoRepo.save(emp);

}

}
