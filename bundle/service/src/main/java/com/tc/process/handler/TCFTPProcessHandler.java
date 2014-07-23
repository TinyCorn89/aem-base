package com.tc.process.handler;

import java.util.Properties;

import javax.jcr.Session;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public abstract class TCFTPProcessHandler {
	static Logger LOG = Logger.getLogger(  TCFTPProcessHandler.class.getName());
	String serverAddress;
	String userId;
	String password;
	String remoteDirectory;
	String localDirectory;
	FTPClient ftp = new FTPClient();

	public abstract boolean singleProcess (Session session, String localPath, String remotePathJournal, String fileName) throws Exception;
	public abstract void disconnect() throws Exception;
	
	public TCFTPProcessHandler(Properties props) {

		// props = new Properties();

		try {

			// props.load(new FileInputStream("properties/" + propertiesFile));

			serverAddress = props.getProperty("ftp.serverAddress").trim();
			userId = props.getProperty("ftp.userId").trim();
			password = props.getProperty("ftp.password").trim();
			remoteDirectory = props.getProperty("ftp.remoteDirectory").trim();
			localDirectory = props.getProperty("ftp.localDirectory").trim();

			// new ftp client

			ftp.setConnectTimeout(Integer.parseInt(props.getProperty("ftp.timeout").trim()));
			// try to connect
			ftp.connect(serverAddress);
			// login to server
			if (!ftp.login(userId, password)) {
				ftp.logout();
				LOG.error("unable to connect");
				return;
			}
			int reply = ftp.getReplyCode();
			// FTPReply stores a set of constants for FTP reply codes.
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				LOG.error("unable to connect");
				return;
			}

			// enter passive mode
			ftp.enterLocalPassiveMode();
			// get system name
			LOG.info("Remote system is " + ftp.getSystemType());
			// change current directory
			ftp.changeWorkingDirectory(remoteDirectory);
			LOG.info("Current directory is "
					+ ftp.printWorkingDirectory());
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		} catch (Exception e) {
			LOG.error("unable to connect"+e);
		}

	}




}
