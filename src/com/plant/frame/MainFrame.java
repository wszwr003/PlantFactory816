package com.plant.frame;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.plant.util.LineDotChart;


public class MainFrame extends JFrame{
	public static String look = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	public static ChartHistoryDataPanel jPanel2;
	MyMenu mm;
	public MainFrame() {
		super("PlantFactory");
		
		try {
			UIManager.setLookAndFeel(look);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setDefaultLookAndFeelDecorated(true);
		
//		URL url = this.getClass().getResource("/images/红灯2020.png");	//set frame logo!
		URL url = this.getClass().getResource("/images/logo.jpg");	//set frame logo!
		Image img = Toolkit.getDefaultToolkit().getImage(url);	
		this.setIconImage(img);
		
		setSize(1366,768);
		setLocationRelativeTo(null);			//locate in center
		setResizable(true);						//set cannot resize
		setDefaultCloseOperation(EXIT_ON_CLOSE);//exit on close
		
		initComponents();

	}
	private void initComponents(){
		
		mm = new MyMenu();
		this.setJMenuBar(mm);
		JTabbedPane jTabbedPane = new JTabbedPane();
		this.add(jTabbedPane);
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(2,3,0,0));
		jPanel.add(new NodeJPanel("node1",1));
		jPanel.add(new NodeJPanel("node2",2));
		jPanel.add(new NodeJPanel("node3",3));
		jPanel.add(new NodeJPanel("node4",4));
		jPanel.add(new NodeJPanel("node5",5));
		jPanel.add(new NodeJPanel("node6",6));
		jTabbedPane.addTab("视频监控",jPanel);
		
		jPanel2 = new ChartHistoryDataPanel();
		jTabbedPane.addTab("数据分析",jPanel2);
		jPanel2.setLayout(null);
		
		SetPanel jPanel3 = new SetPanel(0);
		jTabbedPane.addTab("系统设置",jPanel3);
		jPanel3.setLayout(null);
		
		SetAllTab jPanel4 = new SetAllTab();
		jTabbedPane.addTab("系统设置",jPanel4);

	}
}
