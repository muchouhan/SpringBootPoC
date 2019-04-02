package com.mukund.ldd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
		
		List<LddResult> result = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			result.add(LddResult.builder().id(Integer.toString(i)).description("Desc").build());
		}

		return result;

		// RestTemplate template = new RestTemplate();
		// ResponseEntity<List<LddResult>> lddResponse =
		// template.exchange(APIUrl.LDD_API.url(), HttpMethod.GET, null,
		// new ParameterizedTypeReference<List<LddResult>>() {});
		// return lddResponse.getBody();
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
