package com.mukund.ldd;

import static java.lang.System.exit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mukund.ldd.model.LddResult;
import com.mukund.ldd.service.LddService;

@SpringBootApplication
public class LdDaSApplication implements CommandLineRunner {

	@Autowired
	private LddService service;

	/**
	 * Default application main to bootstrap the Spring Boot application
	 * container.
	 *
	 * @param args
	 *            default command line args
	 */

	public static void main(String[] args) {
		SpringApplication.run(LdDaSApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CddParam param = new CddParam(args);
		List<LddResult> response = service.retrieve(param.getCountryId());
		String file = service.transform(response);
		service.upload(file);
		exit(0);

	}

}
