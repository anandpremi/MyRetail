package com.example.demo;


public class Utility{
	

static String getCurrencySymbol(String country){
	Locale locale = new Locale("EN",country);
        Currency currency = Currency.getInstance(locale).getCurrencyCode();
        return currency;
	}
}

