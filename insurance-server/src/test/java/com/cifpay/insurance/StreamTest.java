package com.cifpay.insurance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamTest {
	
public static void main(String[] args) {
	

    /*try {  
    	File file = new File("d:/back.txt");
    	FileInputStream inputStream = new FileInputStream(file);
        FileInputStream fis = new FileInputStream("d:/back.txt");  
        FileOutputStream fos = new FileOutputStream("e:/test.txt");  
        byte[] b = new byte[1024];  
        try {  
            int length = 0;  
            while ((length = inputStream.read(b)) != -1) {  
                System.out.println(length);  
                fos.write(b, 0, length);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } catch (FileNotFoundException e) {  
        e.printStackTrace();  
    }  */
    
    try {
		FileOutputStream outPut = new FileOutputStream("hello.txt");
		try {
			outPut.write("com.baidu.com".getBytes());
			outPut.close();
			File file = new File("hello.txt");
	    	FileInputStream inputStream = new FileInputStream(file);
			byte[] bye = new byte[1024];
			int len = inputStream.read(bye);
			System.out.println(new String(bye,0,len));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}

}
