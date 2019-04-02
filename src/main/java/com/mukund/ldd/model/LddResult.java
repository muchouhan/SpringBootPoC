package com.mukund.ldd.model;

import lombok.Builder;

public @Builder class LddResult {
	private String id;
	private String description;
	
	public String message(){
		return id+"~"+description;
	}
}
