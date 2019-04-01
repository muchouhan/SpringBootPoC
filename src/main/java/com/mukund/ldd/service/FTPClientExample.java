package com.mukund.ldd.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPClientExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			FTPClient ftp = new FTPClient();

			String serverAddress = "127.0.0.1", userId = "user", password = "password";

			// try to connect
			ftp.connect(serverAddress);

			// login to server
			if (!ftp.login(userId, password)) {
				ftp.logout();
				System.out.println("Login Error");
			}

			int reply = ftp.getReplyCode();
			// FTPReply stores a set of constants for FTP reply codes.
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				System.out.println("Connection Error");
			}

			// enter passive mode
			ftp.enterLocalPassiveMode();
			// get system name
			System.out.println("Remote system is " + ftp.getSystemType());
			// get current directory
			System.out.println("Current directory is " + ftp.printWorkingDirectory());

			// get list of filenames
			FTPFile[] ftpFiles = ftp.listFiles();

			if (ftpFiles != null && ftpFiles.length > 0) {
				// loop thru files
				for (FTPFile file : ftpFiles) {
					if (file.isFile()) {
						System.out.println("File is " + file.getName());
					} else if (file.isDirectory()) {
						System.out.println("Directory is " + file.getName());
					}

				}
			}

			// change current directory
			ftp.changeWorkingDirectory("FTPClientExample");
			System.out.println("Current directory is " + ftp.printWorkingDirectory());

			String localFileFullName = "D:\\JCGExample\\myfile.txt";

			File localFile = new File(localFileFullName);

			FileInputStream input = new FileInputStream(localFile);

			if (ftp.storeFile(localFile.getName(), input)) {
				System.out.println("File Upload Successfull");
			}

			input.close();

			// Create Sub-Directory
			if (ftp.makeDirectory("subdir1")) {
				System.out.println("Directory Creation Successfull");
			} else {
				System.out.println("Directory Creation Failed");
			}

			// get list of filenames
			ftpFiles = ftp.listFiles();

			if (ftpFiles != null && ftpFiles.length > 0) {
				// loop thru files
				for (FTPFile file : ftpFiles) {
					if (file.isFile()) {
						System.out.println("File is " + file.getName());
					} else if (file.isDirectory()) {
						System.out.println("Directory is " + file.getName());
					}

				}
			}

			System.out.println("Uploaded File Content\n[");
			// Getting the File in an InputStream

			InputStream remoteInput = ftp.retrieveFileStream(localFile.getName());
			BufferedReader in = new BufferedReader(new InputStreamReader(remoteInput));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}

			System.out.println("]");

			remoteInput.close();

			// call completePendingCommand and check its return value to verify
			// success. If this is not done, subsequent commands may behave
			// unexpectedly
			if (!ftp.completePendingCommand()) {
				System.out.println("Completing Pending Commands Not Successfull");
			}

			// Download All Files to Local Directory and Delete from Server

			ftpFiles = ftp.listFiles();

			String localDirectory = "D:\\JCGExample\\localDirectory";

			if (ftpFiles != null && ftpFiles.length > 0) {
				// loop thru files
				for (FTPFile file : ftpFiles) {
					if (!file.isFile()) {
						continue;
					}
					System.out.println("File is " + file.getName() + " getting Downloaded");

					// get output stream
					OutputStream output;

					File outfile = new File(localDirectory + "/" + file.getName());
					outfile.createNewFile();

					output = new FileOutputStream(outfile);
					// get the file from the remote system
					ftp.retrieveFile(file.getName(), output);
					// close output stream
					output.close();

					// delete the file
					ftp.deleteFile(file.getName());

					System.out.println("File " + outfile.getName() + " Download Successfull");

				}
			}

			ftp.logout();
			ftp.disconnect();
		} catch (IOException ex) {
			ex.printStackTrace();

		}

	}

}
