package com.mukund.ldd;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CddParam extends DefaultParser {

	CommandLine cmd;

	public CddParam(String... args) {
		
		// create the Options
		Options options = new Options();
		options.addOption("country", true, "country for which LDD service to invoke");
		
		//HelpFormatter formatter = new HelpFormatter();
		//formatter.printHelp("LdDaSApplication", options);
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
