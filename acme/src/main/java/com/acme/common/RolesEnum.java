package com.acme.common;

public enum RolesEnum {
	GOVT ("GOVT"), FSA("FSA") , EHR("EHR"); 
	
	String value;
	RolesEnum(String incoming){
		this.value = incoming;
	}
	
	
}
