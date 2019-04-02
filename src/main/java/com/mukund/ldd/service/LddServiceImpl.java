package com.mukund.ldd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mukund.ldd.builder.DateBuilder;
import com.mukund.ldd.builder.FileBuilder;
import com.mukund.ldd.model.LddResult;

@Service
public class LddServiceImpl implements LddService {

	@Autowired
	private Environment prop;

	@Override
	public List<LddResult> retrieve(String countryId) throws Exception {
		DateBuilder date = new DateBuilder();
		System.out.println("Start date:"+date.getStartDate());
		System.out.println("end date:"+date.getEndDate());
		
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<List<LddResult>> response = template.exchange(prop.getRequiredProperty("ldd.app.url"), HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<LddResult>>() {});
		
		return response.getBody();
		
	}

	@Override
	public List<String> transform(List<LddResult> results) throws Exception {
		return new FileBuilder(prop,results).files();
	}

	@Override
	public Boolean upload(List<String> files) throws Exception {
		FTPUploader uploader = new FTPUploader(prop);
		return uploader.upload(files);
	}
}
