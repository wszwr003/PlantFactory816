package com.plant.frame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartPanel;
/*
 * 实时采集数据界面Frame
 */
public class ChartNowFrame4 extends JFrame{
	public ChartNowFrame4(int id,String chartContent,String title,String yaxisName) {
		try {
			UIManager.setLookAndFeel(MainFrame.look);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultLookAndFeelDecorated(true);
		
		URL url = this.getClass().getResource("/images/logo.jpg");	//set frame logo!
		Image img = Toolkit.getDefaultToolkit().getImage(url);	
		this.setIconImage(img);
		
		setSize(600,450);
		setLocationRelativeTo(null);			//locate in center
		setResizable(true);						//set cannot resize
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);//exit on close
		//setDefaultCloseOperation(EXIT_ON_CLOSE);//exit on close
		setTitle(title+"_Node_"+id);
	    ChartNowDataPanel4 cp = new ChartNowDataPanel4(id,chartContent, title, yaxisName);
		 this.add(cp);
		 Thread cpThread= new Thread(cp);
		 cpThread.start();
		setVisible(true); 						//setVisible放最后不然可能显示不正确！！！！
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				cpThread.stop();
			}
		});
	}


}
