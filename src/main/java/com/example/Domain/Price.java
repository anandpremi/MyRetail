package com.example.demo;



@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
	private UUID id;
	
	private UUID productId;
	
	private Double value; 
	
	private String currency_code; 
	
}
