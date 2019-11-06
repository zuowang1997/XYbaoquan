package model;

import java.io.BufferedInputStream;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class SendCertificate {  
    int port = 8821;  
  
    public void Send(String filepath) {  
        Socket s = null;  
        try {  
            ServerSocket ss = new ServerSocket(port);  
            while (true) {  
            	//http://linshow26.iteye.com/blog/2005476
                // 选择进行传输的文件  
                String filePath = filepath;  
                File fi = new File(filePath);  
  
                System.out.println("文件长度:" + (int) fi.length());  
  
                // public Socket accept() throws  
                // IOException侦听并接受到此套接字的连接。此方法在进行连接之前一直阻塞。  
  
                s = ss.accept();  
                System.out.println("建立socket链接");  
                DataInputStream dis = new DataInputStream(  
                        new BufferedInputStream(s.getInputStream()));  
                dis.readByte();  
  
                DataInputStream fis = new DataInputStream(  
                        new BufferedInputStream(new FileInputStream(filePath)));  
                DataOutputStream ps = new DataOutputStream(s.getOutputStream());  
                // 将文件名及长度传给客户端。这里要真正适用所有平台，例如中文名的处理，还需要加工，具体可以参见Think In Java  
                // 4th里有现成的代码。  
                ps.writeUTF(fi.getName());  
                ps.flush();  
                ps.writeLong((long) fi.length());  
                ps.flush();  
  
                int bufferSize = 8192;  
                byte[] buf = new byte[bufferSize];  
  
                while (true) {  
                    int read = 0;  
                    if (fis != null) {  
                        read = fis.read(buf);  
                        // 从包含的输入流中读取一定数量的字节，并将它们存储到缓冲区数组 b  
                        // 中。以整数形式返回实际读取的字节数。在输入数据可用、检测到文件末尾 (end of file)  
                        // 或抛出异常之前，此方法将一直阻塞。  
                    }  
  
                    if (read == -1) {  
                        break;  
                    }  
                    ps.write(buf, 0, read);  
                }  
                ps.flush();  
                // 注意关闭socket链接哦，不然客户端会等待server的数据过来，  
                // 直到socket超时，导致数据不完整。  
                fis.close();  
                s.close();  
                System.out.println("文件传输完成");  
            }  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    
    
    
    
    
    
}  