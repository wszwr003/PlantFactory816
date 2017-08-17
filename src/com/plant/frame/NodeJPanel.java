package com.plant.frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.html.ImageView;

import com.plant.server.PCAsServer;
import com.plant.util.ExcelPOI;


/*
 * 单一节点界面
 */
public class NodeJPanel extends JPanel {
	public static Vector<NodeJPanel> node_SET = new Vector<NodeJPanel>();
	public static boolean[] isConn= new boolean[6]; 
	public ExcelPOI ePoi;
	
	JLabel temp_jLabel = new JLabel("温度：");
	JLabel humi_jLabel = new JLabel("湿度：");
	JLabel light_jLabel = new JLabel("光照：");
	JLabel co2_jLabel = new JLabel("CO2：");
	JLabel tempstate_jLabel = new JLabel("光照状态");
	JLabel co2valve_jLabel = new JLabel("CO2阀门");
	JLabel feng_jLabel = new JLabel("新风状态");
	
	public JTextField temp_jTextField = new JTextField("0.0",5);
	public JTextField humi_jTextField = new JTextField("0.0",5);
	public JTextField light_jTextField = new JTextField("0.0",5);
	public JTextField co2_jTextField = new JTextField("0.0",5);
	
	public JLabel tempstate_jLabel2 = new JLabel(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
	public JLabel co2valve_jLabel2 = new JLabel(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
	public JLabel feng_jLabel2 = new JLabel(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));

	JButton temp_jButton = new JButton("开始光照");
	JButton co2_jButton = new JButton("通入CO2");
	JButton feng_jButton = new JButton("新风开");
	JButton temp_jButton2 = new JButton("关闭光照");
	JButton co2_jButton2 = new JButton("关闭CO2");
	JButton feng_jButton2 = new JButton("新风关");
	JButton video_jButton = new JButton("拍照");
	public static boolean videobutton = false;
	
	
	public NodeJPanel(String node,int id) {
		ePoi = new ExcelPOI(id);
		node_SET.add(this);
		this.setLayout(null);
		this.setOpaque(false);
		this.setBorder(BorderFactory.createTitledBorder(node));
		
		VideoJPanel video = new VideoJPanel(node);
		(new Thread(video)).start();
		
		temp_jLabel.setBounds(10+330, 20, 70, 30);
		humi_jLabel.setBounds(10+330, 20+30, 70, 30);
		light_jLabel.setBounds(10+330, 20+60, 70, 30);
		co2_jLabel.setBounds(10+330, 20+90, 70, 30);
		
		tempstate_jLabel.setBounds(55+330, 50+90+0, 70, 50);
		co2valve_jLabel.setBounds(55+330, 50+120+20, 70, 50);
		feng_jLabel.setBounds(55+330, 50+150+40, 70, 50);
		
		temp_jTextField.setBounds(40+330, 20, 70, 30);
		humi_jTextField.setBounds(40+330, 20+30, 70, 30);
		light_jTextField.setBounds(40+330, 20+60, 70, 30);
		co2_jTextField.setBounds(40+330, 20+90, 70, 30);
		
		tempstate_jLabel2.setBounds(10+330, 50+90+0, 50, 50);
		co2valve_jLabel2.setBounds(10+330, 50+120+20, 50, 50);
		feng_jLabel2.setBounds(10+330, 50+150+40, 50, 50);
		
		video.setBounds(10, 25, 320, 240);
		
		temp_jButton.setBounds(10, 270, 100, 30);
		co2_jButton.setBounds(120, 270, 100, 30);
		feng_jButton.setBounds(230, 270, 100, 30);
		temp_jButton2.setBounds(10, 300, 100, 30);
		co2_jButton2.setBounds(120, 300, 100, 30);
		feng_jButton2.setBounds(230, 300, 100, 30);
		video_jButton.setBounds(340, 300, 100, 30);
		
		temp_jTextField.setEditable(false);
		temp_jTextField.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("mouseClicked,temp_jTextField");
				new ChartNowFrame(id,"1","实时温度","℃");
			}
		});
		
		humi_jTextField.setEditable(false);
		humi_jTextField.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("mouseClicked,humi_jTextField");
				new ChartNowFrame2(id,"2","实时湿度","%");
			}
		});
		
		light_jTextField.setEditable(false);
		light_jTextField.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("mouseClicked,light_jTextField");
				new ChartNowFrame3(id,"3","实时光照强度","umol/m2/s");
			}
		});
		
		co2_jTextField.setEditable(false);
		co2_jTextField.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("mouseClicked,co2_jTextField");
				new ChartNowFrame4(id,"4","实时二氧化碳浓度","ppm");
			}
		});
		
		temp_jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				PCAsServer.sendtothread(id,"asd", PCAsServer.zhen_RelayOn);
				PCAsServer.relayControl(id,(char)0x01,(char)0x02,(char)0x02);
				tempstate_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯open.png")));
				
			}
		});	
		
		co2_jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PCAsServer.relayControl(id,(char)0x02,(char)0x01,(char)0x02);
				co2valve_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯open.png")));
			}
		});	
		
		feng_jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PCAsServer.relayControl(id,(char)0x02,(char)0x02,(char)0x01);
				feng_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯open.png")));
			}
		});	
		temp_jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				PCAsServer.sendtothread(id,"asd", PCAsServer.zhen_RelayOn);
				PCAsServer.relayControl(id,(char)0x00,(char)0x02,(char)0x02);
				tempstate_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
			}
		});	
		
		co2_jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PCAsServer.relayControl(id,(char)0x02,(char)0x00,(char)0x02);
				co2valve_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
			}
		});	
		
		feng_jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PCAsServer.relayControl(id,(char)0x02,(char)0x02,(char)0x00);
				feng_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
			}
		});	
		video_jButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PCAsServer.zhen_Video[3]=(char)id;
                PCAsServer.sendtothread(id,"", PCAsServer.zhen_Video);    //视频采集指令
                
                videobutton =true;
			}
		});	
		//video_jButton.setEnabled(false);
		
		this.add(temp_jLabel);
		this.add(humi_jLabel);
		this.add(light_jLabel);
		this.add(co2_jLabel);
		
		this.add(tempstate_jLabel);		
		this.add(co2valve_jLabel);		
		this.add(feng_jLabel);
		
		this.add(temp_jTextField);
		this.add(humi_jTextField);
		this.add(light_jTextField);
		this.add(co2_jTextField);
		this.add(tempstate_jLabel2);		
		this.add(co2valve_jLabel2);
		this.add(feng_jLabel2);
		
		this.add(video);
		this.add(temp_jButton);
		this.add(co2_jButton);
		this.add(feng_jButton);
		this.add(temp_jButton2);
		this.add(co2_jButton2);
		this.add(feng_jButton2);
		this.add(video_jButton);
	}
}
