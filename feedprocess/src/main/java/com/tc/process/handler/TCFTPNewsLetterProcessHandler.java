package com.tc.process.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.log4j.Logger;

public class TCFTPNewsLetterProcessHandler  extends TCFTPProcessHandler {

	static Logger LOG = Logger.getLogger(  TCFTPNewsLetterProcessHandler.class.getName());
	Properties props ;
	public TCFTPNewsLetterProcessHandler(Properties props){
		super(props);
		this.props=props;
	}

	@Override
	public boolean process() throws Exception {
		LOG.info("Staring the FTP process");
		
		try {
			// get only xml files
			FTPFileFilter filter = new FTPFileFilter() {

				@Override
				public boolean accept(FTPFile ftpFile) {

					Date date;
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.MONTH, Integer.parseInt(props.getProperty("ftp.month")));
                    cal.set(Calendar.DATE,Integer.parseInt (props.getProperty("ftp.day")));
                    cal.set(Calendar.YEAR, Integer.parseInt(props.getProperty("ftp.year")));
                    cal.set(Calendar.HOUR,00);
                    cal.set(Calendar.MINUTE,00);
                    cal.set(Calendar.SECOND,00);
                    date = cal.getTime();
                   return (ftpFile.isFile() && ftpFile.getName().contains(".xml")&&ftpFile.getTimestamp().getTime().after(date));
				}
			};

			// get list of filenames
			FTPFile[] ftpFiles = ftp.listFiles(remoteDirectory, filter);
			LOG.info("LOCAL directory"+localDirectory);
            
            File localDir = new File(localDirectory);
            if (localDir.exists()) {
                clearDirectory(localDir);
                 //clear the content of the directory
			 } else {
               localDir.mkdirs();
             }

			//will copy images directly to output folder
			 File OutDirectory = new File(localDirectory+File.separator+"output");
			 if (!OutDirectory.exists()) {
                OutDirectory.mkdirs();
                 
			 }

            int filesDownloded =0;
			if ((ftpFiles != null) && (ftpFiles.length > 0)) {
				// loop thru files
				for (FTPFile file : ftpFiles) {
					if (!file.isFile()) {
						continue;
					}
					LOG.info("File is " + file.getName());

					// TODO . All needed is a string to find out Images in the
					// output stream
					OutputStream output = new FileOutputStream(localDirectory
							+ File.separator + file.getName());
					// get the file from the remote system
					ftp.retrieveFile(file.getName(), output);
					// close output stream
					output.close();
                    //increment the counter
                    filesDownloded++;
					String fileContents = readFile(localDirectory + File.separator
							+ file.getName());

					// get the images like /05803481197747_low.jpg .at presetn
					// assumption is they are in same directory
					Pattern pattern = Pattern.compile("[^/]+\\.jpg");
					Matcher matcher = pattern.matcher(fileContents);
					while (matcher.find()) {

						LOG.info("Images in the file"+matcher.group());
						OutputStream imageOutput = new FileOutputStream(
								localDirectory + File.separator +"output"+File.separator+ matcher.group());
						// get the file from the remote system
						ftp.retrieveFile(matcher.group(), imageOutput);
						// close output stream
						imageOutput.close();
					}

				}
			}

             if (filesDownloded ==0) {
              LOG.info(" zero files downloded .");
              return false;
              }
			LOG.info("Download complete");
		} catch (Exception ex) {
			LOG.error("error in download"+ex);
			throw  ex;
		} finally {
			try {
				ftp.logout();
				ftp.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOG.error("error in download"+e);
			}

		}
		return true;
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
    for (File file: dir.listFiles()) {
        if (file.isDirectory()) clearDirectory(file);
        file.delete();
    }
    }
}
