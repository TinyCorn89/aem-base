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
	public static void main(String[] args) throws Exception {

		selectPdf(args[0]);

	}

	// allow images selection for converting
	public static void selectPdf(String name) throws Exception {
		File sourceFile = new File(name);
		convertPDFToJPG(sourceFile.toString());
	}

	public static void convertPDFToJPG(String src)
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
			ImageIO.write(bi, "jpg", new File("pdfimage" + count + ".jpg"));
			count++;
            System.out.println("Please wait..."+count);
		}
         
		System.out.println("Conversion complete");

		
	}

}
