package model;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.awt.*; 
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mysql.jdbc.util.Base64Decoder;
import com.sun.image.codec.jpeg.*;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import javax.imageio.*; 
import library.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;





public class make_certificate{
	
public Connection getCon(){
	Connection conn=null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		try {
			conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1;databaseName=DataSecurity","sa","12080003");
			System.out.println("database connect Succ !");  
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("database connect Failed !"+e.toString());
			e.printStackTrace();
			return null;
		}
	
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block 
		e.printStackTrace();
		return null;
	}
}


public boolean checkid(String id){
	Connection conn=getCon();
	Statement st; 
	String sql="";	  
	boolean fu = false;
	try {  
		sql = "select * from DataSecurity..UserInformation where UserID='"+id+"'";
		st = (Statement) conn.createStatement();
		ResultSet rs = st.executeQuery(sql); 
	    while(rs.next())
	    {
	    	fu = true;	            
	    }	   
	    conn.close();
	    } 
	catch (SQLException e) {  
	    System.out.println(e);
	    fu = false;
	    }
	      
	if(fu == true){return true;}
	else {return false;}
	       
}





//将文章分为512位一段
public String[]  Grouping(String beforeGrouping){ 
	String BeforeGrouping = beforeGrouping;
	int inptLength = BeforeGrouping.length();
	int strLength;
	boolean b = true;
	if(inptLength%512 == 0)
	{
		strLength = inptLength/512;
		}
	else{
		b = false;
		strLength = (int)inptLength/512 + 1;
		}
	String[] str = new String[strLength];
	for(int i=0; i<str.length;i++)
	{ 
		if(b||(i!=str.length-1))
			str[i] = BeforeGrouping.substring(512*i,512*i+512);
		else
			str[i] = BeforeGrouping.substring(512*i,BeforeGrouping.length()-1);
	}
	return str;
}

//SHA-512哈希算法
public String doHash(String beforeHash){
	SHA512 sha = new SHA512();
    String afterHash = sha.SHA(beforeHash,"SHA-512"); 
	System.out.println(afterHash);
	return afterHash;
}


public String doTimeStamp(String beforeStamp){
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 	Timestamp nowtime = new Timestamp(System.currentTimeMillis()); 
	String afterStamp = beforeStamp + nowtime;
	System.out.println(afterStamp);
	return afterStamp;
}

//添加用户信息后再做哈希运算  ？？？？
public String addUserInformation(String after1Hash,String UserID,String FileName,Timestamp SecurityTime){
    String SecurityTimeStr = String.valueOf(SecurityTime);
	String afterAddUserInfor = after1Hash + UserID + FileName + SecurityTimeStr;
	System.out.println(afterAddUserInfor);
	return afterAddUserInfor;
}









//添加证书时用的
public void writeFileToDb(String ID,String SecurityTime,String fileName){
   System.out.println("Writing to database... from file:"+fileName);
   Connection con = null;
   try{
      con = getCon();
      File myFile = new File(fileName);
      InputStream in = new FileInputStream(myFile);
      
      String sql = "update DataSecurity..SecurityNotes set Certificate=? where ID='"+ID+"' and SecurityTime='"+SecurityTime+"' ";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setBinaryStream(1,in,(int)myFile.length());
      int result = ps.executeUpdate();
      if (result>0)
         System.out.println("Writing to database picfile success");
      else
         System.out.println("Writing to database picfile error");  
      in.close(); 
   }
   
   catch(Exception ex){
      ex.printStackTrace();
   }
   
   finally{
      if (con !=null){
         try{
            con.close();
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
    con = null;
   }
   }
}
//删除证书时用的，用来将证书图片替换成“已删除”图片
public void writeFileToDb_havedeleted(String deleteCertificateCode){
	   Connection con = null;
	   try{
	      con = getCon();
	      File myFile = new File("C:\\XinYouData\\muban\\havedeleted.jpg");
	      InputStream in = new FileInputStream("C:/XinYouData/MakeCertificate/"+myFile);
	      String sql = "update DataSecurity..SecurityNotes set Certificate=? where CertificateCode='"+deleteCertificateCode+"' ";
	      PreparedStatement ps = con.prepareStatement(sql);
	      ps.setBinaryStream(1,in,(int)myFile.length());
	      int result = ps.executeUpdate();
	      if (result>0)
	         System.out.println("Writing to database picfile success");
	      else
	         System.out.println("Writing to database picfile error");  
	      in.close(); 
	   }
	   
	   catch(Exception ex){
	      ex.printStackTrace();
	   }
	   
	   finally{
	      if (con !=null){
	         try{
	            con.close();
	         }
	         catch(Exception ex){
	            ex.printStackTrace();
	         }
	    con = null;
	   }
	   }
	}
//修改证书时用的，以CertificateCode来查找
public void writeFileToDb_byCertificateCode(String CertificateCode,String fileName){
	   System.out.println("Writing to database... from file:"+fileName);
	   Connection con = null;
	   try{
	      con = getCon();
	      File myFile = new File(fileName);
	      InputStream in = new FileInputStream("C:/XinYouData/MakeCertificate/"+myFile);
	      String sql = "update DataSecurity..SecurityNotes set Certificate=? where CertificateCode='"+CertificateCode+"' ";
	      PreparedStatement ps = con.prepareStatement(sql);
	      ps.setBinaryStream(1,in,(int)myFile.length());
	      int result = ps.executeUpdate();
	      if (result>0)
	         System.out.println("Writing to database picfile success");
	      else
	         System.out.println("Writing to database picfile error");  
	      in.close(); 
	   }
	   
	   catch(Exception ex){
	      ex.printStackTrace();
	   }
	   
	   finally{
	      if (con !=null){
	         try{
	            con.close();
	         }
	         catch(Exception ex){
	            ex.printStackTrace();
	         }
	    con = null;
	   }
	   }
	}




/**  
 * 功能： 拷贝image从一个地址至另一个地址  
 * @param sourcePath   
 *                图片初始生成地址  
 * @param toPath  
 *                图片要复制到的地方  
 * @param FileName  
 *                图片的名字  
 * @param FileN  
 *                生成的文件夹名字  
 */  
public boolean CopyImage(String start,String end)
{
      try {  
            //要拷贝的图片
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(start)));
             //目标的地址
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(end)));  
             try {  
                 int val = -1;  
                 while((val=bis.read())!=-1){  
                    
                     bos.write(val);  
                 }  
                 bos.flush();  
                 bos.close();  
                 bis.close(); 
                 return true;
            } catch (IOException e) {  
            e.printStackTrace();              }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } 
        return false;
}



public boolean deleteFile(String fileName) {  
	  File file = new File(fileName);
	  // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除   
	  if (file.exists() && file.isFile()) {  
	   if (file.delete()) {  
	    System.out.println("删除单个文件" + fileName + "成功！");  
	    return true;  
	   } else {  
	    System.out.println("删除单个文件" + fileName + "失败！");  
	    return false;  
	   }  
	  } else {  
	   System.out.println("删除单个文件失败：" + fileName + "不存在！");  
	   return false;  
	  }  
	 }








public int readImgFromDb(String id,String newName){
   System.out.println("Reading from database to file:"+newName);
   Connection con = null;
   int i=0;
   try{
      con = getCon();
      String setime = null;
      String certificate = null;
      String setime_jian = null;
      Statement st = con.createStatement();
      st = (Statement) con.createStatement();
      ResultSet rs = st.executeQuery("select Certificate,SecurityTime from DataSecurity..SecurityNotes where ID='"+id+"' ");
      while(rs.next())
      {
      	setime = rs.getString("SecurityTime");
      	certificate = rs.getString("Certificate");
      	setime_jian = setime.substring(0,10)+setime.substring(11,13)+setime.substring(14,16)+setime.substring(17,19)+setime.substring(20,setime.length());
      	OutputStream out = null;
        InputStream ins = null;
  	    out = new FileOutputStream("C:/XinYouData/"+id+"+"+setime_jian+".jpg");
        int tmpi=0;
        ins= rs.getBinaryStream("Certificate");
        while((tmpi=ins.read())!=-1){
          out.write(tmpi);
      }
         i++;
         System.out.println("Reading from database to file "+newName+" success");
         ins.close();
         out.flush();
         out.close();
      }
   }
   
   catch(Exception ex){
      ex.printStackTrace();
   }
   
   finally{
      if (con!=null){
         try{
            con.close();
         }
         catch(Exception ex){
            ex.printStackTrace();
         }
      }
   con = null;
   }
   return i;
}






 
    /**
     * 把图片印刷到图片上
     * 
     * @param pressImg --
     *            水印文件
     * @param targetImg --
     *            目标文件
     * @param x
     *            --x坐标
     * @param y
     *            --y坐标
     */
    public void pressImage(String pressImg, String targetImg,
            int x, int y) {
        try {
            //目标文件
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
 
            //水印文件
            File _filebiao = new File(pressImg);
            Image src_biao = ImageIO.read(_filebiao);
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.drawImage(src_biao, x, y, wideth_biao, height_biao, null);
            //水印文件结束
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 打印文字水印图片
     * 
     * @param pressText
     *            --文字
     * @param targetImg --
     *            目标图片
     * @param fontName --
     *            字体名
     * @param fontStyle --
     *            字体样式
     * @param color --
     *            字体颜色
     * @param fontSize --
     *            字体大小
     * @param x --
     *            偏移量
     * @param y
     */
 
    public void pressText(String pressText, String targetImg,
            String fontName, int fontStyle, int color, int fontSize, int x,
            int y) {
        try {
            File _file = new File(targetImg);
            String str=_file.getAbsolutePath(); 
            System.out.println("AbsolutePath："+str);
            
            Image src = ImageIO.read(_file);   
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            // String s="www.qhd.com.cn";
            g.setColor(Color.RED);
            g.setFont(new Font(fontName, fontStyle, fontSize));
 
            g.drawString(pressText, wideth - fontSize - x, height - fontSize
                    / 2 - y);
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    
    public void ErWeiMa(String CCode) { 
    	String text = CCode;      
    	int width = 120;        
    	int height = 120;          
    	//二维码的图片格式      
    	String format = "jpg";    
    	Hashtable hints = new Hashtable();    
    	//内容所使用编码      
    	hints.put(EncodeHintType.CHARACTER_SET, "utf-8");      
    	BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(text,     
			       BarcodeFormat.QR_CODE, width, height, hints);
			//生成二维码       
			File outputFile = new File("C:/XinYouData/MakeCertificate"+File.separator+"new.jpg");     
	    	try {
				MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		} catch (WriterException e1) {
			e1.printStackTrace();
		}
    	
    }  
    
    
    
    
    public String PictureToString(File picfile) throws IOException{
    	File PictureFile = picfile;
    	
    	String ImageFileFormat = "jpg";
    	StringBuffer sb1 = null;
    	BufferedImage buffImage = null;
    	ByteArrayOutputStream baos = null;
    	byte[] byteImage = null;
    	String splitSymbol = ",";
    	
    	sb1 = new StringBuffer();
    	  try {
    	   buffImage = ImageIO.read(PictureFile);
    	   baos = new ByteArrayOutputStream();
    	   ImageIO.write(buffImage, ImageFileFormat, baos);
    	  } catch (IOException e) {
    	   e.printStackTrace();
    	  }
    	  byteImage = baos.toByteArray();
    	  BASE64Encoder encoder = new BASE64Encoder();
    	  String aaa = encoder.encode(byteImage);
    	  buffImage.flush();
    	  baos.flush();
    	  baos.close();
    	  return aaa;// 返回Base64编码过的字节数组字符串
    }
    
    
    
    
    public static boolean StringToPicture(String imgStr,String imgFile) throws Exception{// 对字节数组字符串进行Base64解码并生成图片
    	if (imgStr == null) // 图像数据为空
    	return false;
    	BASE64Decoder decoder = new BASE64Decoder();
    	try {
    	// Base64解码
    	byte[] b = decoder.decodeBuffer(imgStr);
    	for (int i = 0; i < b.length; ++i) {
    	if (b[i] < 0) {// 调整异常数据
    	b[i] += 256;
    	}
    	}
    	// 生成jpeg图片
    	String imgFilePath = imgFile;// 新生成的图片
    	OutputStream out = new FileOutputStream(imgFilePath);
    	out.write(b);
    	out.flush();
    	out.close();
    	return true;
    	} catch (Exception e) {
    	throw e;
    	}
    	}
    
    
    
    
    
    public String findCertificateFromDb(String CerCode){
    	   Connection con = null;
    	   String CerName = null;
    	   try{
    	      con = getCon();
    	      String userid = null;
    	      String setime = null;
    	      String certificate = null;
    	      String setime_jian = null;
    	      Statement st = con.createStatement();
    	      st = (Statement) con.createStatement();
    	      ResultSet rs = st.executeQuery("select ID,Certificate,SecurityTime from DataSecurity..SecurityNotes where CertificateCode='"+CerCode+"' ");
    	      while(rs.next())
    	      {
    	    	userid = rs.getString("ID");
    	      	setime = rs.getString("SecurityTime");
    	      	certificate = rs.getString("Certificate");
    	      	setime_jian = setime.substring(0,10)+setime.substring(11,13)+setime.substring(14,16)+setime.substring(17,19)+setime.substring(20,setime.length());
    	      	OutputStream out = null;
    	        InputStream ins = null;
    	  	    out = new FileOutputStream("C:/XinYouData/"+userid+"+"+setime_jian+".jpg");
    	        int tmpi=0;
    	        ins= rs.getBinaryStream("Certificate");
    	        while((tmpi=ins.read())!=-1){
    	          out.write(tmpi);
    	      }
    	         System.out.println("Show Certificate success！");
    	         CerName = userid+"+"+setime_jian+".jpg";
    	         ins.close();
    	         out.flush();
    	         out.close();
    	      }
    	   }
    	   
    	   catch(Exception ex){
    	      ex.printStackTrace();
    	   }
    	   
    	   finally{
    	      if (con!=null){
    	         try{
    	            con.close();
    	         }
    	         catch(Exception ex){
    	            ex.printStackTrace();
    	         }
    	      }
    	   con = null;
    	   }
    	   return CerName;
    	}
    
    
    
    
    
    public int setup(File file) throws IOException {  
  	  
    	RandomAccessFile raf = new  RandomAccessFile(file,  "r" );  
        FileChannel channel = raf.getChannel();  
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0 , channel  
                .size());  
        PDFFile pdffile = new  PDFFile(buf);  
  
        System.out.println("页数： "  + pdffile.getNumPages());  
        int pagemunber = pdffile.getNumPages();
  
        for  ( int  i =  1 ; i <= pdffile.getNumPages(); i++) {  
            // draw the first page to an image   
            PDFPage page = pdffile.getPage(i);  
  
            // get the width and height for the doc at the default zoom   
            Rectangle rect = new  Rectangle( 0 ,  0 , ( int ) page.getBBox()  
                    .getWidth(), (int ) page.getBBox().getHeight());  
  
            // generate the image   
            Image img = page.getImage(rect.width, rect.height, // width &   
                                                                // height   
                    rect, // clip rect   
                    null ,  // null for the ImageObserver   
                    true ,  // fill background with white   
                    true   // block until drawing is done   
                    );  
  
            BufferedImage tag = new  BufferedImage(rect.width, rect.height,  
                    BufferedImage.TYPE_INT_RGB);  
            tag.getGraphics().drawImage(img, 0 ,  0 , rect.width, rect.height,  
                    null );  
            FileOutputStream out = new  FileOutputStream(  
                    "c://"+ i + ".jpg" );  // 输出到文件流   
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            encoder.encode(tag); // JPEG编码   
  
            channel.close();
            tag.flush();
            buf.clear();
            out.flush();
            out.close();   
            
        }  
        
        return pagemunber;
    }
    

    
  
    
    
    





}
