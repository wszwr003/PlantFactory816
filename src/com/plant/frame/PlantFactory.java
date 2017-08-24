package com.plant.frame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.plant.server.PCAsServer;

public class PlantFactory {
	public static String look = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	static MainFrame mf ;
	static PCAsServer pcs ;
	
	
	public static void main(String[] args) throws Exception{
		mf = new MainFrame();
		new LoginFrame(mf);
		new PCAsServer();
	}
	
//	public static void main(String[] args){
//		 SwingUtilities.invokeLater(new Runnable() {       //保证gui在事件分发线程中的创建 
//	            public void run() {
//	        		mf = new MainFrame();
//	        		new LoginFrame(mf);
//	        		try {
//						new PCAsServer();
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//	            }
//	        });
//	}
}
 