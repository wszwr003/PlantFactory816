package com.plant.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyUtil {
	static byte[] buf= new byte[2048000];
	public static void copyDoc(String src,String dis) {
			// TODO Auto-generated method stub
			/**
			* ����һ��ͼƬ 1�����ж�Ҳ��дFileInputStream��FileOutputStream 2�����Ƿ��ı�����
			* ʹ�õ����ֽ��������ڲ����ļ��Ķ���
			*/
			try {
			//1�������Ƶ�Դ�ļ�
			FileInputStream fis = new FileInputStream(src);
			//2������֮��������µ��ļ�
			FileOutputStream fos = new FileOutputStream(dis);
			// 3���Զ��建��������
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
