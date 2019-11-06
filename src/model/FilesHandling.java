package model;

 
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Calendar;

import javax.imageio.ImageIO;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfCopy;  
import com.lowagie.text.pdf.PdfImportedPage;  
import com.lowagie.text.pdf.PdfReader; 
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;





public class FilesHandling {
	
//没看懂
	public String GetFileFinishTime(File file){
		File f = file; 

		Calendar cal = Calendar.getInstance(); 
		long time = f.lastModified(); 
		cal.setTimeInMillis(time); 
		String finishtime = cal.getTime().toLocaleString();
		System.out.println("文件完成时间： " + cal.getTime().toLocaleString()); 
		
		return finishtime;
		
	}
	
	
	
	 public void renameFile(String oldname,String newname){ 
	        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
	            File oldfile=new File(oldname); 
	            File newfile=new File(newname); 
	            if(!oldfile.exists()){
	                return;//重命名文件不存在
	            }
	            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
	                System.out.println(newname+"已经存在！"); 
	            else{ 
	                oldfile.renameTo(newfile); 
	            } 
	        }else{
	            System.out.println("新文件名和旧文件名相同...");
	        }
	    }
	 
	 
	 
	 //读取PDF文件中的字符串
		 
		 public String GetTextFromPdf(File filename) throws Exception
		 {
		  FileInputStream instream = new FileInputStream(filename);    // 根据指定文件创建输入流
		  PDFParser parser = new PDFParser( instream );                // 创建PDF解析器
		  parser.parse();                                              // 执行PDF解析过程
		  
		  PDDocument pdfdocument = parser.getPDDocument();             // 获取解析器的PDF文档对象
		  PDFTextStripper pdfstripper = new PDFTextStripper();         // 生成PDF文档内容剥离器
		  String contenttxt = pdfstripper.getText(pdfdocument);        // 利用剥离器获取文档
		  
		  System.out.println("文件长度 : "+ contenttxt.length() +"\n");
		  instream.close();
		  pdfdocument.close();
		  return contenttxt;
		  
		 }
	 
	 
	 
	 //jpg生成pdf
	 public void jpgToPdf(String pdfFile,String imgFile)  throws Exception {
		 try {
				String imagePath = imgFile;
				String pdfPath = pdfFile;
				BufferedImage img = ImageIO.read(new File(imagePath));
				FileOutputStream fos = new FileOutputStream(pdfPath);
				Document doc = new Document(null, 0, 0, 0, 0);
				doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
				Image image = Image.getInstance(imagePath);
				PdfWriter.getInstance(doc, fos);
				doc.open();
				doc.add(image);
				doc.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (BadElementException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		  }
	 
	 
	 //生成pdf文件
	 public void createPDF(String filename,String daima) {     
		 // step 1     
		 Document document = new Document(PageSize.A4);     
		 // step 2     
		 try {       
			 PdfWriter.getInstance(document, new FileOutputStream(filename));               
			 document.addTitle("ID.NET");       
			 document.addAuthor("xinyou");        
			 document.addSubject("xinyou");        
			 document.addKeywords("xinyou");               
			 // step 3       
			 document.open();       
			 // step 4       
			 document.add(new Paragraph(daima)); 
			 document.add(new Paragraph("    "));
			 document.add(new Paragraph("Thank you for using our data preservation platform！"));  
			 } catch (FileNotFoundException e) {       
				 e.printStackTrace();     
				 } 
		      catch (DocumentException e) {       
		    	  e.printStackTrace();     
		    	  } 
		      finally {       
		    	  // step 5       
		    	  document.close();     
		    	  }   
		 }

	 
	 
	 
	 //两个pdf合成一个
	 public boolean mergePdfFiles(String[] files, String newfile) {  
	        boolean retValue = false;  
	        Document document = null;  
	        try {  
	            document = new Document(new PdfReader(files[0]).getPageSize(1));  
	            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));  
	            document.open();  
	            for (int i = 0; i < files.length; i++) {  
	                PdfReader reader = new PdfReader(files[i]);  
	                int n = reader.getNumberOfPages();  
	                for (int j = 1; j <= n; j++) {  
	                    document.newPage();  
	                    PdfImportedPage page = copy.getImportedPage(reader, j);  
	                    copy.addPage(page);  
	                }  
	            }  
	            retValue = true;  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            document.close();  
	        }  
	        return retValue;  
	    }  
	 
	 
	 
	 
	     @SuppressWarnings("resource")
		public long getFileSizes(File f) throws Exception{ //取得文件大小
	        long s=0;
	        FileInputStream fis = null;
	        if (f.exists()) {
	            fis = new FileInputStream(f);
	           s= fis.available();
	        } else {
	            f.createNewFile();
	            System.out.println("文件不存在");
	        }
	        fis.close();
	        return s;
	     }
	 
	 
	 

	       
	
	
	
	
	
	
	
	
	
	
	
}