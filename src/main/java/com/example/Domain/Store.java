package com.example.demo;



@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Store {
	
	@Id
	private UUID storeId;
	
    private String storeName;
    
    private UUID locationId;
    
    
	
}
