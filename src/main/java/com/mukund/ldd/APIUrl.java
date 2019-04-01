package com.mukund.ldd;

public enum APIUrl {

	LDD_API("/ldd/{id}");

	private String url;

	APIUrl(String url) {
		this.url = url;
	}

	public String url() {
		return url;
	}

}
