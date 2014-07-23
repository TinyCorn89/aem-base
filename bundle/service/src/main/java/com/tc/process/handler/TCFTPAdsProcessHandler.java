package com.tc.process.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.log4j.Logger;

import com.day.cq.commons.jcr.JcrUtil;

public class TCFTPAdsProcessHandler extends TCFTPProcessHandler{

	static Logger LOG = Logger.getLogger(TCFTPAdsProcessHandler.class
			.getName());
	Properties props;
	
	

	public TCFTPAdsProcessHandler(Properties props) {
		super(props);
		this.props = props;
	}
	
	@Override
	public void disconnect() throws Exception{
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOG.error("error in download" + e);
		}
	}
	
	
	@Override
	public boolean singleProcess(Session session, String locaPath, String remotePathJournal, String fileName)throws Exception {
		 
		
		String ftpFileName = remoteDirectory + "/" + remotePathJournal + "/" + fileName;
		FTPFile[] ftpFile = ftp.listFiles(ftpFileName);
		if ((ftpFile != null) && (ftpFile.length > 0)) {
			InputStream iStream = ftp.retrieveFileStream(ftpFileName);
			if(iStream!=null){
				if(!session.nodeExists(locaPath+"/"+fileName)){
					LOG.info(" FILE : "+ftpFileName);
					Binary binary = session.getValueFactory().createBinary(iStream);
					Node imageDam = JcrUtil.createPath(locaPath+"/"+fileName,"nt:resource",session);
					imageDam.setProperty("jcr:data", binary);
					imageDam.setProperty("jcr:lastModified", Calendar.getInstance());
					imageDam.setProperty("jcr:mimeType", "image/jpeg");
					session.save();
					LOG.info("Dam Path : "+ imageDam.getPath());
					return true;
				}
			}
		}
		return false;
	}
	
}
