package com.tc.process.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.log4j.Logger;

public class TCFTPCPProcessHandler extends TCFTPProcessHandler {

	static Logger LOG = Logger.getLogger(TCFTPCPProcessHandler.class
			.getName());
	Properties props;
	
	

	public TCFTPCPProcessHandler(Properties props) {
		super(props);
		this.props = props;
	}

	@Override
	public boolean process() throws Exception {
		LOG.info("Staring the FTP process");

		try {
			
			File localDir = new File(localDirectory);
			if (localDir.exists()) {
				clearDirectory(localDir);
				// clear the content of the directory
			} else {
				localDir.mkdirs();
			}
			
			
			LOG.info("LOCAL directory" + localDirectory);
			
			// will copy images directly to output folder
			File OutDirectory = new File(localDirectory);
			if (!OutDirectory.exists()) {
				OutDirectory.mkdirs();

			}
			
			download("", remoteDirectory);
			LOG.info("Download complete");
		} catch (Exception ex) {
			LOG.error("error in download" + ex);
			throw ex;
		} finally {
			try {
				ftp.logout();
				ftp.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error("error in download" + e);
			}

		}
		return true;
	}

	protected void download(String parentDir, String remoteDirectory) throws IOException,
			FileNotFoundException {
		
		FTPFileFilter filter = new FTPFileFilter() {

			@Override
			public boolean accept(FTPFile ftpFile) {
				if (ftpFile.isDirectory()) {
					return true;
				}
				return (ftpFile.isFile()
						&& ftpFile.getName().contains(".xml"));
			}
		};
		FTPFile[] ftpFiles = ftp.listFiles(parentDir + "/" + remoteDirectory, filter);
		int filesDownloded = 0;
		if ((ftpFiles != null) && (ftpFiles.length > 0)) {
			// loop thru files
			for (int i = 0; (i < ftpFiles.length); i++) {
				FTPFile file = ftpFiles[i];
				
				String ftpFileName = file.getName();
				if (StringUtils.equalsIgnoreCase(ftpFileName, ".") || StringUtils.equalsIgnoreCase(ftpFileName, "..")) {
					continue;
				}
				if (file.isDirectory()) {
					LOG.info(file.getName()
							+ " is a directory, iterating through this directory");
					
					download(remoteDirectory, file.getName());
					
					continue;
				}
				LOG.info("File is " + file.getName());

				// TODO . All needed is a string to find out Images in the
				// output stream
				OutputStream output = new FileOutputStream(localDirectory
						+ File.separator + file.getName());
				
				
				ftpFileName = parentDir + "/" + remoteDirectory +"/" + file.getName();
				LOG.info("Downloading " + ftpFileName);
				// get the file from the remote system
				ftp.retrieveFile(ftpFileName, output);
				// close output stream
				output.close();
				//ftp.deleteFile(ftpFileName);
				// increment the counter
				filesDownloded++;
				String fileContents = readFile(localDirectory + File.separator
						+ file.getName());

				Pattern pattern = Pattern.compile("[^/]+\\.jpg");
				Matcher matcher = pattern.matcher(fileContents);
				
				while (matcher.find()) {
					String imageName = parentDir + "/" + remoteDirectory + "/" + matcher.group();
					LOG.info("Images in the file" + imageName);
					OutputStream imageOutput = new FileOutputStream(localDirectory
									+ File.separator + matcher.group());
					// get the file from the remote system
					ftp.retrieveFile(imageName, imageOutput);
					// close output stream
					imageOutput.close();
					
					/*
					 uncomment this after development is finished
					boolean deleted = ftp.deleteFile(imageName);
					System.out.println(imageName + " deleted =" + deleted);
					*/
				}

			}
		}

		if (filesDownloded == 0) {
			LOG.info(" zero files downloded .");

		}
		
	}

	private String readFile(String file) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		reader.close();
		return stringBuilder.toString();

	}

	private void clearDirectory(File dir) {
		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				clearDirectory(file);
			file.delete();
		}
	}
}
