package com.example.demo;



@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	
	@Id
	private UUID locationId;
	
	private String addressLine1; 
	
	private String addressLine2; 
	
	private String city;
	
	private String state; 
	
	private String country; 
	
	private String zipCode; 
	
}
