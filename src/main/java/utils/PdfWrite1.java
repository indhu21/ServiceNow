package utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;


import wrapper.GenericWrappers;
import wrapper.ServiceNowWrappers;

public class PdfWrite1 extends GenericWrappers{

	public static void createNewPdf(String pdfName) {

		// Get the pdf file name
		File file = new File(getAbsolutePath()+"/reports/"+pdfName);

		// check if exist, delete it
		try {
			if(file.exists())
				new File(file.getCanonicalPath()).delete();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create a document and add a page to it
		PDDocument doc = new PDDocument();

		// Save the results and ensure that the document is properly closed:
		try {
			doc.save(getAbsolutePath()+"/reports/"+pdfName);	
			doc.close();
		} catch (COSVisitorException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public static void appendReport(String pdfName, String desc) {
		appendReport(pdfName, desc, true);
	}

	public static void appendReport(String pdfName, String desc, boolean bSnapshot) {

		PDDocument doc = null;
		PDPage page;
		Boolean bSuite = false;
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		for (int i = 0; i < stElements.length; i++) {
//			System.out.println();
			if(stElements[i].getClassName().contains("SuiteMethods")){
				bSuite = true;
				break;
			}
		}
		
		if(!bSuite)
			desc = "Step: "+sStepNumber+" - "+desc;
		
		System.out.println(desc);

		
		try
		{
			doc = PDDocument.load(getAbsolutePath()+"/reports/"+pdfName);

			// Create a new font object selecting one of the PDF base fonts

			
//			PDFont fontBold = PDType1Font.HELVETICA_BOLD;
			PDFont fontBold = PDTrueTypeFont.loadTTF(doc, new File( getAbsolutePath()+"/lib/arial.ttf" ));
//			fontBold.setWidths(PDType1Font.HELVETICA.getWidths());
			
			//we will add the image to the first page.
			page =  new PDPage();
			doc.addPage(page);
		
			PDXObjectImage ximage = null;
			PDPageContentStream cos = new PDPageContentStream(doc, page, true, true);

			// Define a text content stream using the selected font, move the cursor and draw some text
			if(desc.length() > 70){

				int numWraps = (desc.length() / 70)+1;
				int startPos = 0;
				int endPos = 70;
				int lastIndex;
				for (int i = 1; i<=numWraps; i++) {

					// find the position of the white spaces from the last position
					if(endPos != desc.length()){
						lastIndex = desc.substring(startPos, endPos).lastIndexOf("\n");
						if(lastIndex == -1)
							lastIndex = desc.substring(startPos, endPos).lastIndexOf(" ");
					}else
						lastIndex = -1;

					// if there is no white spaces (specially at the end line. lastIndex will be end position
					if(lastIndex == -1){
						lastIndex = desc.length();
						endPos = desc.length();
					}else{
						lastIndex = startPos + lastIndex;
					}
					//System.out.println("desc :"+desc.substring(startPos, endPos));

					//System.out.println("Last Index :"+lastIndex);
					//System.out.println("Starting position:"+startPos);
					// start writing
					cos.beginText();
					cos.setFont(fontBold, 13);

					// adjust the height of the font
					cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - (50+(20*(i-1))));

					// Get the sub string for the next line
					//System.out.println(desc);
					//System.out.println(startPos);
					//System.out.println(lastIndex);

					try {
						cos.drawString(desc.substring(startPos, lastIndex));
					} catch (Exception e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
					}
					//System.out.println("line: "+(desc.substring(startPos, lastIndex)));
					cos.endText();				

					// reset the start and end position
					startPos = lastIndex+1;
					if(startPos+90 >= desc.length() )
						endPos = desc.length();
					else
						endPos = startPos+70;

					//System.out.println("start position :"+startPos);
					//System.out.println("end position :"+endPos);

				}


			} else{
				cos.beginText();
				cos.setFont(fontBold, 13);

				cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 50);
				cos.drawString(desc);
				cos.endText();
			}


			if(bSnapshot){
				try {
					BufferedImage awtImage = null;
					try {
						Dimension screenResolution = new Dimension(1200,800);
						awtImage = new Robot().createScreenCapture(new Rectangle(screenResolution));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					}
					

					// add an image
					try {
						driver = new ServiceNowWrappers().getDriver();
						File src = driver.getScreenshotAs(OutputType.FILE);	        
						
						awtImage = ImageIO.read(src);						
						
					} catch (FileNotFoundException fnfex) {

					} catch (WebDriverException e) {

					} catch (Exception e){
						e.printStackTrace();
					}
					ximage = new PDPixelMap(doc, awtImage);
					float scale = 0.4f; // alter this value to set the image size
					cos.drawXObject(ximage, 20, 360, ximage.getWidth()*scale, ximage.getHeight()*scale);
					
				} catch (Exception e){
					e.printStackTrace();
				}
			}

			cos.close();
			doc.save(getAbsolutePath()+"/reports/"+pdfName);
			sStepNumber++;
		}
		catch (COSVisitorException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		{
			if( doc != null )
			{
				try {
					doc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


}