package com.plant.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyUtil {
	static byte[] buf= new byte[2048000];
	public static void copyDoc(String src,String dis) {
			// TODO Auto-generated method stub
			/**
			* 复制一个图片 1、既有读也有写FileInputStream和FileOutputStream 2、还是非文本数据
			* 使用到了字节流中用于操作文件的对象
			*/
			try {
			//1、被复制的源文件
			FileInputStream fis = new FileInputStream(src);
			//2、复制之后产生的新的文件
			FileOutputStream fos = new FileOutputStream(dis);
			// 3、自定义缓冲区对象
			//byte[] buf = new byte[10240000];
			int by = 0;
				while ((by = fis.read(buf)) != -1) {
				fos.write(buf, 0, by);
				fos.close();
				fos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
