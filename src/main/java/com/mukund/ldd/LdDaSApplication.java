package com.mukund.ldd;

import static java.lang.System.exit;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
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

	public static void main(String[] args) {
		SpringApplication.run(LdDaSApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CddParam param = new CddParam(args);
		List<LddResult> response = service.retrieve(param.getCountryCode());
		List<String> files = service.transform(response);
		service.upload(files);
		System.out.println("Job Completed!!!");
		exit(0);
	}
	
	private class CddParam extends DefaultParser {
		CommandLine cmd;
		public CddParam(String... args) {
			// create the Options
			Options options = new Options();
			options.addOption("country", true, "country for which LDD service to invoke");
			try {
				// parse the command line arguments
				cmd = parse(options, args);
				
				if (cmd.hasOption("country")) {
					System.out.println("country:"+cmd.getOptionValue("country"));
				}
				
			} catch (ParseException exp) {
				System.out.println("Unexpected exception:" + exp.getMessage());
			}
		}
		
		public String getCountryCode(){
			if (cmd.hasOption("country")) {
				return cmd.getOptionValue("country");
			}else{
				return "all";
			}
		}
	}
}
