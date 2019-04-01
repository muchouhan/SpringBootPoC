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
		//options.addOption("startDate", "all", true, "Last execution date.");
		//options.addOption("endDate", "almost-all", true, "end date for records ..");
		options.addOption("countryId", "all", true, "country for which LDD service to invoke");
		//HelpFormatter formatter = new HelpFormatter();
		//formatter.printHelp("LdDaSApplication", options);
		try {
			// parse the command line arguments
			cmd = parse(options, args);
			
			if (cmd.hasOption("countryId")) {
				System.out.println("countryId:"+cmd.getOptionValue("countryId"));
			}
			
		} catch (ParseException exp) {
			System.out.println("Unexpected exception:" + exp.getMessage());
		}

	}
	
	public String getCountryId(){
		if (cmd.hasOption("countryId")) {
			return cmd.getOptionValue("countryId");
		}else{
			return "all";
		}
	}

}
