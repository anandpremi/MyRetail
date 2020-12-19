package com.example.demo;



@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	private UUID productId;
	
	private String productName;
	
	private String model;
	
	private String brand; 
	
	private String description;  
	
	private Integer availQuantity;
	
	private String productImage;
	
	private Store store;
	
	@Transient
	private Price current_price;
	
}
