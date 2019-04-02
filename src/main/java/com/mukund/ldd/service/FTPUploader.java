package com.mukund.ldd.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.env.Environment;

public class FTPUploader {

	private Environment prop;

	public FTPUploader(Environment prop) throws IOException {
		this.prop = prop;
		open();
	}

	private void open() throws IOException {

	}

	public boolean upload(List<String> files) throws IOException {
		return true;
	}

}