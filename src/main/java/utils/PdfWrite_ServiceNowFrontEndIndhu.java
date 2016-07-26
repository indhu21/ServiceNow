package utils;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;

import wrapper.ServiceNowWrappers;

public class PdfWrite_ServiceNowFrontEndIndhu extends ServiceNowWrappers{
	
	// Babu added for getting first PDF page
	static ServiceNowWrappers snw = new ServiceNowWrappers();
	
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

	// Babu updated for getting first PDF page
	public static void appendReport(String pdfName, String desc, boolean bSnapshot) {

		PDDocument doc = null;
		PDPage page;

		if(!desc.contains(testcaseName))
			desc = "Step: "+sStepNumber+" - "+desc;

		System.out.println(desc);

		try
		{
			doc = PDDocument.load(getAbsolutePath()+"/reports/"+pdfName);

			// Create a new font object selecting one of the PDF base fonts
			PDFont fontBold = PDType1Font.HELVETICA_BOLD;

			//we will add the image to the first page.
			page =  new PDPage();
			doc.addPage(page);

			PDXObjectImage ximage = null;
			PDXObjectImage header = null;
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
					cos.setFont(fontBold, 12);

					// adjust the height of the font
					cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - (100+(20*(i-1))));

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
					if(startPos+70 >= desc.length() )
						endPos = desc.length();
					else
						endPos = startPos+70;


				}


			} else{
				cos.beginText();
				cos.setFont(fontBold, 12);

				cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 100);
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
					}


					// add an image
					try {
						
						File src = snw.getDriver().getScreenshotAs(OutputType.FILE);	        
						awtImage = ImageIO.read(src);						

					} catch (FileNotFoundException fnfex) {

					} catch (WebDriverException e) {

					} catch (Exception e){
						e.printStackTrace();
					}
					
					ximage = new PDPixelMap(doc, awtImage);
					
					float scale = 0.4f; // alter this value to set the image size
					
					cos.drawXObject(ximage, 20, 360, ximage.getWidth()*scale, ximage.getHeight()*scale);
					
					// Babu added for getting first PDF page
					header = new PDPixelMap(doc,ImageIO.read(new File(getAbsolutePath()+"/data/header.png")));
					float resize = 0.45f; // alter this value to set the image size
					cos.drawXObject(header, 20, 720, header.getWidth()*0.5f, header.getHeight()*resize);	


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


	// Babu added for getting first PDF page
	public static void appendFirstPage(String testName, String testDescription, String startDate, String endDate, long time, String status) {

		PDDocument doc = null;
		PDPage page;

		try
		{
			doc = PDDocument.load(getAbsolutePath()+"/reports/"+testName+".pdf");

			// Create a new font object selecting one of the PDF base fonts
			PDFont fontBold = PDType1Font.HELVETICA_BOLD;
			PDFont font = PDType1Font.HELVETICA;

			//we will add the image to the first page.
			page =  new PDPage();
			doc.addPage(page);

			PDXObjectImage header = null;
			PDPageContentStream cos = new PDPageContentStream(doc, page, true, true);

			cos.beginText();
			cos.setFont(fontBold, 12);

			// adjust the height of the font
			cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 100);

			cos.drawString(testDescription);
			cos.endText();

			cos.beginText();
			cos.setFont(font, 12);

			// adjust the height of the font
			cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 140);

			cos.drawString("Test started on:"+"            "+startDate);
			cos.endText();

			// adjust the height of the font
			cos.beginText();
			cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 160);

			cos.drawString("Test completed on:"+"       "+endDate);
			cos.endText();

			// adjust the height of the font
			cos.beginText();
			cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 180);

			cos.drawString("Duration:"+"                       "+time+" Seconds");
			cos.endText();

			// adjust the height of the font
			cos.beginText();
			cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 200);

			cos.drawString("Instance:"+"                       "+sUrl);
			cos.endText();

			// adjust the height of the font
			cos.beginText();
			cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 220);
			
						
			cos.drawString("Browser:"+"                       "+snw.getDriver().getCapabilities().getBrowserName().toUpperCase());
			cos.endText();

			// adjust the height of the font
			cos.beginText();
			cos.moveTextPositionByAmount(20, page.getMediaBox().getHeight() - 240);

			cos.drawString("Status:"+"                          "+status.toUpperCase());
			cos.endText();

			
			header = new PDPixelMap(doc,ImageIO.read(new File(getAbsolutePath()+"/data/header.jpeg")));
			float resize = 0.45f; // alter this value to set the image size
			cos.drawXObject(header, 20, 720, header.getWidth()*0.5f, header.getHeight()*resize);	
			  
			cos.close();
			doc.save(getAbsolutePath()+"/reports/"+testName+".pdf");
			doc.close();
			
			PDDocument newDoc = new PDDocument();
			doc = PDDocument.load(getAbsolutePath()+"/reports/"+testName+".pdf");
			List<?> allPages = doc.getDocumentCatalog().getAllPages();
			
	        // Code to rearrange the list goes here
			newDoc.addPage( ( PDPage )allPages.get ( allPages.size()-1) );
	        for ( int curPageCnt = 0; curPageCnt < allPages.size()-1; curPageCnt++ ){
	        	newDoc.addPage( ( PDPage )allPages.get ( curPageCnt  ) );
	        }
	        newDoc.save(getAbsolutePath()+"/reports/"+testName+".pdf");
	        newDoc.close();

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