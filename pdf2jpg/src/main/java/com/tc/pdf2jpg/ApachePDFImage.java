package com.tc.pdf2jpg;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class ApachePDFImage {

    /**
     *
     * @param args[0] - pdffilename to be converted into jpeg
     * @throws Exception
     */
	public static void main(String[] args) throws Exception {
		//ar[0] -filename

		convertPDF(args[0]);

	}

	// allow images selection for converting
	public static void convertPDF(String name) throws Exception {
		File sourceFile = new File(name);
		convertPDFToImages(sourceFile.toString());
	}

	public static void convertPDFToImages(String src)
			throws FileNotFoundException, IOException {


		// load pdf file in the document object
		PDDocument doc = PDDocument.load(new FileInputStream(src));

		System.out.println("Source="+src);
		List<PDPage> pages = doc.getDocumentCatalog().getAllPages();
		// create iterator object so it is easy to access each page from the
		// list
		Iterator<PDPage> i = pages.iterator();
		int count = 1; // count variable used to separate each image file
		// Convert every page of the pdf document to a unique image file
		System.out.println("Please wait...");
		while (i.hasNext()) {
			PDPage page = i.next();
			BufferedImage bi = page.convertToImage();
			StringBuilder imageName=new StringBuilder (src.substring(0, src.lastIndexOf('.'))).append("_image").append(count).append(".jpg");
			ImageIO.write(bi, "jpg", new File(imageName.toString()));
			 System.out.println("completed..."+count);
			count++;

		}

		System.out.println("Conversion complete");


	}

}
