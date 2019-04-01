package com.mukund.ldd.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetFileLastModifiedExample {
	
	public static void main(String[] args) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
		Date date = new Date();
	    
		BufferedWriter writer = new BufferedWriter(new FileWriter("ldd_execution.date"));
	    writer.write(dateFormat.format(date));
	    writer.close();
	    
	    BufferedReader reader = new BufferedReader(new FileReader("ldd_execution.date"));
	    System.out.println("FIle time:"+reader.readLine());
	    reader.close();
	}
}
