package com.example.demo;

public interface PriceRepository extends ReactiveCouchbaseRepository<Price, UUID> {
	
	@Query("#{#n1ql.selectEntity} where productId= $1")
	Mono<Price> getPrice(UUID productId);
	
	@Query("update `myretail` set value = $2 where productId = $1")
	Mono<Price> getPrice(UUID productId, Double price);
}