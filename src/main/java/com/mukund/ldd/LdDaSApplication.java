package com.mukund.ldd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mukund.ldd.service.LddService;

import static java.lang.System.exit;

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
		service.retrieve(args[0].toString());
		
		if (args.length > 0) {
			//
		} else {
			//System.out.println(service.getMessage());
		}

		exit(0);

	}

}
