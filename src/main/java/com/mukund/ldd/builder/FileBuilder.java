package com.mukund.ldd.builder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.env.Environment;

import com.mukund.ldd.model.LddResult;

public class FileBuilder {

	private List<String> paths;
	private Environment prop;
	private Date date;
	
	public FileBuilder(Environment prop,List<LddResult> results) {
		this.prop=prop;
		date = new Date();

		List<String> paths = new ArrayList<>();
		paths.add(buildResponseFile());
		paths.add(buildOkFile());
		paths.add(buildEODFile());
	}

	private String buildResponseFile() {
		// Get the file reference
		
		Path path = Paths.get(prop.getRequiredProperty("ldd.ftp.temp.folder") + "//"
				+ prop.getRequiredProperty("ldd.ftp.filename") + ".txt");

		// Use try-with-resource to get auto-closeable writer instance
//		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
//			results.stream().forEach(item -> {
//				try {
//					writer.write(item.message() + "\n");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
//		}

		return "";
	}

	private String buildOkFile() {
		return "";
	}

	private String buildEODFile() {
		return "";
	}

	public List<String> files() {
		return paths;
	}

}
