package com.tc.process.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;



public class TCNewLetterTransformerHandler {
	static Logger LOG = Logger.getLogger(  TCNewLetterTransformerHandler.class.getName());

	public boolean tranform(String inputdir ,String xslFileName) throws Exception {
		try {
			 File directory = new File(inputdir);
			 File OutDirectory = new File(directory.getAbsolutePath()+File.separator+"output");
			 if (!OutDirectory.exists()) {
				 OutDirectory.mkdir();
			 }
			 //TODO below is a hack to avoid dtd errors .need to find a solution
			 InputStream in = this.getClass().getClassLoader().getResourceAsStream("CPNewsML.dtd");

			 OutputStream os = new FileOutputStream(directory.getAbsolutePath()+File.separator+File.separator+"CPNewsML.dtd");

	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            //read from is to buffer
	            while((bytesRead = in.read(buffer)) !=-1){
	                os.write(buffer, 0, bytesRead);
	            }
              in.close();os.close();
             // do the same thing for .xsl file as well .Some reason it is not working if the file is not present in the processed dir.
             
             InputStream inxsl = this.getClass().getClassLoader().getResourceAsStream(xslFileName);
             xslFileName=directory.getAbsolutePath()+File.separator+File.separator+xslFileName;
			 OutputStream osXsl = new FileOutputStream(xslFileName);

	            byte[] buffer1 = new byte[1024];
	            int bytesRead1;
	            //read from is to buffer
	            while((bytesRead1 = inxsl.read(buffer1)) !=-1){
	                osXsl.write(buffer1, 0, bytesRead1);
	            }
              inxsl.close();osXsl.close();
			 //
           
			 //StreamSource stylesource = new StreamSource(xslFileAsStream);
			 StreamSource source ;
			 File[] directoryListing = directory.listFiles();
			  if (directoryListing != null) {
			    for (File child : directoryListing) {
			    	if (!child.getName().contains(".xml") ) {
						continue;
					}
			    	source= new StreamSource(child);
			    	 LOG.info(" in the directory"+child.getName());
			    	TransformerFactory factory = TransformerFactory.newInstance();
				      
                      //factory.setURIResolver(resloader);
			    	StreamSource stylesource = new StreamSource(xslFileName);
                    Transformer transformer = factory.newTransformer(stylesource);

				      StreamResult result = new StreamResult(new FileOutputStream(OutDirectory.getAbsolutePath()+File.separator+child.getName().replace(".xml", ".jcr.xml")));
				      transformer.transform(source, result);
			    }
			  } else {
			    LOG.info("no files in the directory");
			  }

             // if (directoryListing.)
			FileOutputStream fos = new FileOutputStream(
					OutDirectory.getAbsolutePath()+File.separator+OutDirectory.getName() + ".zip");
				ZipOutputStream zos = new ZipOutputStream(fos);
				File[] outdirectoryListing = OutDirectory.listFiles();
				for (File file : outdirectoryListing) {
					if (!file.isDirectory()) { // we only zip files, not directories
						if (!file.getName().contains(".zip")) {
							addToZip(OutDirectory, file, zos);
						}
					}
				}

				zos.close();
				fos.close();


		    } catch (Exception e) {
		     LOG.error("error in transform",e);
		      throw e;
		    }
		return true;
	}

	private  void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
	IOException {

		FileInputStream fis = new FileInputStream(file);

		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		LOG.info("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);

		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

}
