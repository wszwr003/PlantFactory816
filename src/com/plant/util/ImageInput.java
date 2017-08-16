package com.plant.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;

public class ImageInput {
	public ImageInput() {
		 byte2image(image2byte("C:\\Users\\Administrator\\Desktop\\logo.jpg"),"C:\\Users\\Administrator\\Desktop\\logo2.jpg");
	}

	//图片到byte数组
	  public static byte[] image2byte(String path){
	    byte[] data = null;
	    FileImageInputStream input = null;
	    try {
	      input = new FileImageInputStream(new File(path));
	      ByteArrayOutputStream output = new ByteArrayOutputStream();
	      byte[] buf = new byte[1024];
	      int numBytesRead = 0;
	      while ((numBytesRead = input.read(buf)) != -1) {
	      output.write(buf, 0, numBytesRead);
	      }
	      data = output.toByteArray();
	      output.close();
	      input.close();
	    }
	    catch (FileNotFoundException ex1) {
	      ex1.printStackTrace();
	    }
	    catch (IOException ex1) {
	      ex1.printStackTrace();
	    }
	    return data;
	  }  
	 
	//byte数组到图片
	  public static void byte2image(byte[] data,String path){
	    if(data.length<3||path.equals("")) return;
	    try{
	    FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
	    imageOutput.write(data, 0, data.length);
	    imageOutput.close();
	    System.out.println("Make Picture success,Please find image in " + path);
	    } catch(Exception ex) {
	      System.out.println("Exception: " + ex);
	      ex.printStackTrace();
	    }
	  }
	  
	  public static byte[] byteMerger(byte[] byte_1, byte[] byte_2){  
	        byte[] byte_3 = new byte[byte_1.length+byte_2.length];  
	        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);  
	        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);  
	        return byte_3;  
	    }  
}
