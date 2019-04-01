package com.mukund.ldd.service;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mukund.ldd.APIUrl;
import com.mukund.ldd.model.LddResult;

@Service
public class LddServiceImpl implements LddService {

	@Value("${name:unknown}")
	private String name;

	@Override
	public List<LddResult> retrieve(String countryId) throws Exception {
		System.out.println("startDate():"+startDate());
		System.out.println("endDate():"+endDate());
		return null;
		//RestTemplate template = new RestTemplate();
		//ResponseEntity<List<LddResult>> lddResponse = template.exchange(APIUrl.LDD_API.url(), HttpMethod.GET, null,
		//		new ParameterizedTypeReference<List<LddResult>>() {});
		//return lddResponse.getBody();
	}

	@Override
	public Boolean transform(List<LddResult> results) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(LddResult.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(results, new File("ldd.xml"));
		
		return true;
	}

	@Override
	public Boolean upload(String file) throws Exception {
		FTPUtils ftp = new FTPUtils("localhost", 22, "user", "password");
		ftp.open();
		return null;
	}
}
