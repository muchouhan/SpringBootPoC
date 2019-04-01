package com.mukund.ldd.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mukund.ldd.model.LddResult;

public interface LddService {
	List<LddResult> retrieve(String countryId) throws Exception;
	String transform(List<LddResult> results)throws Exception;
	Boolean upload(String file)throws Exception;
	
	default String startDate(){
		try(BufferedReader reader = new BufferedReader(new FileReader("ldd_execution.date"));){
			return reader.readLine();
		}catch(IOException e){		
			System.out.println("'ldd_execution.date' file doesn't exist!");
		}
	    return new SimpleDateFormat("dd-MM-yy HH-mm-ss").format(new Date());
	}
	
	default String endDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("ldd_execution.date"));){
			writer.write(dateFormat.format(date));
			return dateFormat.format(date);
		}catch(IOException e){		
			System.out.println("exception while writing date in 'ldd_execution.date' file!");
		}
		
	    return new SimpleDateFormat("dd-MM-yy HH-mm-ss").format(new Date());
	}
}
