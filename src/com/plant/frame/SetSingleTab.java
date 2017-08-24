package com.plant.frame;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;

import com.plant.server.PCAsServer;
import com.plant.util.TimerMinute;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SetSingleTab extends JPanel{
	boolean warnFlag = false;
	int node;
	JSpinner spFrom;
	JSpinner spTo;
	JSpinner spFromFeng;
	JSpinner spToFeng;
	JTextField temp;
	JTextField temp_2;
	JTextField humi;
	JTextField humi_2;
	JTextField co2;
	JTextField co2_2;
	JTextField co2AlarmLow;
	JTextField co2AlarmHigh;
	JTextField tempAlarmLow;
	JTextField tempAlarmHigh;
	JButton OK,OpenEXL,OpenPicture;
	TimerTask timetask;
	Thread time;
	Thread WarningT;
	Thread setT;
	JLabel alarmJ;
	JLabel alarmJ2;
	final JFrame frame = new JFrame("JOptionPane");
	public SetSingleTab(int node) {
		//光照设置----灯开关
		//温度控制----空调系统
		//湿度控制----新风系统
		//二氧化碳浓度设置----电磁阀门
		
		//二氧化碳报警阈值设定
		//温度报警阈值设定\
		this.node=node;
		this.setLayout(null);
		this.setVisible(true);
	
		//光照设置----灯开关
		JPanel dataLight = new JPanel();
		dataLight.setBorder(BorderFactory.createTitledBorder("光照设置"));
		dataLight.setBounds(0, 0, 700, 150);
		dataLight.setLayout(null);
		add(dataLight);
		
		JPanel dataSet = new JPanel();
		dataSet.setBorder(BorderFactory.createTitledBorder("温湿度设置"));
		dataSet.setBounds(0, 300, 700, 150);
		dataSet.setLayout(null);
		add(dataSet);
		
		JPanel dataFeng = new JPanel();
		dataFeng.setBorder(BorderFactory.createTitledBorder("新风设置"));
		dataFeng.setBounds(0, 150, 700, 150);
		dataFeng.setLayout(null);
		add(dataFeng);
		
		JPanel dataAlarm = new JPanel();
		dataAlarm.setBorder(BorderFactory.createTitledBorder("报警设置"));
		dataAlarm.setBounds(0, 450,700, 150);
		dataAlarm.setLayout(null);
		add(dataAlarm);
		
		JLabel jl1 = new JLabel("开灯时间:");
		jl1.setBounds(10,55,200,40);
		dataLight.add(jl1);
		SpinnerDateModel model2 = new SpinnerDateModel();
		model2.setCalendarField(Calendar.WEEK_OF_MONTH);
		spFrom = new JSpinner(model2);
		JSpinner.DateEditor editor2 = new JSpinner.DateEditor(
				spFrom,"HH:mm");
		spFrom.setEditor(editor2);
		spFrom.setBounds(110,55,200,40);
		dataLight.add(spFrom);
		
		JLabel jl1_2 = new JLabel("关灯时间:");
		jl1_2.setBounds(350,55,200,40);
		dataLight.add(jl1_2);
		SpinnerDateModel model3 = new SpinnerDateModel();
		model3.setCalendarField(Calendar.WEEK_OF_MONTH);
		spTo = new JSpinner(model3);
		JSpinner.DateEditor editor3 = new JSpinner.DateEditor(
				spTo, "HH:mm");
		spTo.setEditor(editor3);
		spTo.setBounds(450, 55, 200, 40);
		dataLight.add(spTo);
		
		JLabel jl1_feng = new JLabel("新风开启:");
		jl1_feng.setBounds(10,55,200,40);
		dataFeng.add(jl1_feng);
		SpinnerDateModel modelf = new SpinnerDateModel();
		modelf.setCalendarField(Calendar.WEEK_OF_MONTH);
		spFromFeng = new JSpinner(modelf);
		JSpinner.DateEditor editorf = new JSpinner.DateEditor(
				spFromFeng,"HH:mm");
		spFromFeng.setEditor(editorf);
		spFromFeng.setBounds(110,55,200,40);
		dataFeng.add(spFromFeng);
		
		JLabel jl1_f = new JLabel("新风关闭:");
		jl1_f.setBounds(350,55,200,40);
		dataFeng.add(jl1_f);
		SpinnerDateModel modelf2 = new SpinnerDateModel();
		modelf2.setCalendarField(Calendar.WEEK_OF_MONTH);
		spToFeng = new JSpinner(modelf2);
		JSpinner.DateEditor editorf2 = new JSpinner.DateEditor(
				spToFeng, "HH:mm");
		spToFeng.setEditor(editorf2);
		spToFeng.setBounds(450, 55, 200, 40);
		dataFeng.add(spToFeng);
		
		JLabel jl1_3 = new JLabel("温度报警指示:");
		jl1_3.setBounds(740,65,200,40);
		add(jl1_3);
		alarmJ = new JLabel(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
		alarmJ.setBounds(840,65,50,50);
		this.add(alarmJ);
		JLabel jl1_32 = new JLabel("co2报警指示:");
		jl1_32.setBounds(940,65,200,40);
		add(jl1_32);
		alarmJ2 = new JLabel(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
		alarmJ2.setBounds(1020,65,50,50);
		this.add(alarmJ2);
		//温度控制----空调系统//湿度控制----新风系统//二氧化碳浓度设置----电磁阀门
		
		JLabel jl2 = new JLabel("温度上限:                          ℃");
		jl2.setBounds(60, 30,200,40);
		dataSet.add(jl2);
		temp = new JTextField("50");
		temp.setBounds(130, 30, 80, 40);
		dataSet.add(temp);
		JLabel jl2_2 = new JLabel("温度下限:                          ℃");
		jl2_2.setBounds(60, 80,200,40);
		dataSet.add(jl2_2);
		temp_2 = new JTextField("0");
		temp_2.setBounds(130, 80, 80, 40);
		dataSet.add(temp_2);
		
		JLabel jl3 = new JLabel("湿度上限:                          %");
		jl3.setBounds(260, 30,200,40);
		dataSet.add(jl3);
		humi = new JTextField("100");
		humi.setBounds(330, 30, 80, 40);
		dataSet.add(humi);
		JLabel jl3_2 = new JLabel("湿度下限:                          %");
		jl3_2.setBounds(260, 80,200,40);
		dataSet.add(jl3_2);
		humi_2 = new JTextField("0");
		humi_2.setBounds(330, 80, 80, 40);
		dataSet.add(humi_2);
		
		
		JLabel jl4 = new JLabel("CO2上限:                          ppm");
		jl4.setBounds(460, 30,200,40);
		dataSet.add(jl4);
		co2 = new JTextField("4000");
		co2.setBounds(460+70, 30, 80, 40);
		dataSet.add(co2);
		JLabel jl4_2 = new JLabel("CO2下限:                          ppm");
		jl4_2.setBounds(460, 80,200,40);
		dataSet.add(jl4_2);
		co2_2 = new JTextField("0");
		co2_2.setBounds(460+70, 80, 80, 40);
		dataSet.add(co2_2);
		
		//二氧化碳报警阈值设定//温度报警阈值设定
		JLabel jl5 = new JLabel("CO2报警阈值:                          ppm                          ppm");
		jl5.setBounds(20+40, 30,400,40);
		dataAlarm.add(jl5);
		co2AlarmLow = new JTextField("0");
		co2AlarmLow.setBounds(130+40, 30, 80, 40);
		dataAlarm.add(co2AlarmLow);
		co2AlarmHigh = new JTextField("4000");
		co2AlarmHigh.setBounds(260+40, 30, 80, 40);
		dataAlarm.add(co2AlarmHigh);
		
		JLabel jl6 = new JLabel("温度报警阈值:                          ℃                          ℃");
		jl6.setBounds(20+40,80, 400, 40);
		dataAlarm.add(jl6);	
		tempAlarmLow = new JTextField("0");
		tempAlarmLow.setBounds(130+40,80, 80, 40);
		dataAlarm.add(tempAlarmLow);
		tempAlarmHigh = new JTextField("50");
		tempAlarmHigh.setBounds(250+40,80, 80, 40);
		dataAlarm.add(tempAlarmHigh);
		
		OpenEXL = new JButton("打开EXL数据");
		OpenEXL.setBounds(800, 450, 100, 40);
		this.add(OpenEXL);
		OpenEXL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File file=new File("D://Node_1.xls");
				//File file=new File("../excel"); 
				File file=new File("excel"); 
				try {
					java.awt.Desktop.getDesktop().open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		OpenPicture = new JButton("打开图片");
		OpenPicture.setBounds(900, 450, 100, 40);
		this.add(OpenPicture);
		OpenPicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File file=new File("../picture"); 
				File file=new File("picture"); 
				try {
					java.awt.Desktop.getDesktop().open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		OK = new JButton("设定");
		OK.setBounds(600, 600, 80, 40);
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!warnFlag) {
					warnFlag = true;
					spFrom.setEnabled(false);
					spTo.setEnabled(false);
					spFromFeng.setEnabled(false);
					spToFeng.setEnabled(false);
					temp.setEnabled(false);
					humi.setEnabled(false);
					co2.setEnabled(false);
					temp_2.setEnabled(false);
					humi_2.setEnabled(false);
					co2_2.setEnabled(false);
					co2AlarmLow.setEnabled(false);
					co2AlarmHigh.setEnabled(false);
					tempAlarmLow.setEnabled(false);
					tempAlarmHigh.setEnabled(false);
					
			    
			        time = new Time();
					WarningT =new Warning();
					setT =new Set();
					time.start();
					WarningT.start();
					setT.start();
				}else{
					warnFlag = false;
					spFrom.setEnabled(true);
					spTo.setEnabled(true);
					spFromFeng.setEnabled(true);
					spToFeng.setEnabled(true);
					temp.setEnabled(true);
					humi.setEnabled(true);
					co2.setEnabled(true);
					temp_2.setEnabled(true);
					humi_2.setEnabled(true);
					co2_2.setEnabled(true);
					co2AlarmLow.setEnabled(true);
					co2AlarmHigh.setEnabled(true);
					tempAlarmLow.setEnabled(true);
					tempAlarmHigh.setEnabled(true);
					alarmJ.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
					alarmJ2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
					time.stop();
					WarningT.stop();
					setT.stop();
					
				}
					
			}
		});
		this.add(OK);
		jl1.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl1_2.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl1_3.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl2.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl3.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl4.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl2_2.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl3_2.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl4_2.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl5.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl6.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		
	}
	class Time extends Thread{
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		public void run() {
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (TimerMinute.getCurrentTime3().equals(sdf.format(spFrom.getValue()))) {
				PCAsServer.relayControl(node,(char)0x01,(char)0x02,(char)0x02);
				NodeJPanel.node_SET.get(node-1).tempstate_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯open.png")));
			}
			if(TimerMinute.getCurrentTime3().equals(sdf.format(spTo.getValue()))){
				PCAsServer.relayControl(node,(char)0x00,(char)0x02,(char)0x02);
				NodeJPanel.node_SET.get(node-1).tempstate_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));

			}
			if(TimerMinute.getCurrentTime3().equals(sdf.format(spFromFeng.getValue()))){
				PCAsServer.relayControl(node,(char)0x02,(char)0x02,(char)0x01);
				NodeJPanel.node_SET.get(node-1).feng_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯open.png")));
			}
			if(TimerMinute.getCurrentTime3().equals(sdf.format(spToFeng.getValue()))){
				PCAsServer.relayControl(node,(char)0x02,(char)0x02,(char)0x00);
				NodeJPanel.node_SET.get(node-1).feng_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));

			}
			}
		}
	}
	class Warning extends Thread{
		public void run() {
//			JOptionPane.showMessageDialog(null,"二氧化碳高于设定阈值",  "报警",JOptionPane.INFORMATION_MESSAGE); 
//			JOptionPane.showMessageDialog(null,"二氧化碳低于设定阈值",  "报警",JOptionPane.INFORMATION_MESSAGE); 
//			JOptionPane.showMessageDialog(null,"温度高于设定阈值"   ,  "报警",JOptionPane.INFORMATION_MESSAGE); 
//			JOptionPane.showMessageDialog(null,"温度低于设定阈值"   ,  "报警",JOptionPane.INFORMATION_MESSAGE); 
			while(true){
//				Toolkit.getDefaultToolkit().beep();
				
				if (Float.parseFloat(tempAlarmLow.getText())>Float.parseFloat(NodeJPanel.node_SET.get(node-1).temp_jTextField.getText())
						||Float.parseFloat(tempAlarmHigh.getText())<Float.parseFloat(NodeJPanel.node_SET.get(node-1).temp_jTextField.getText())) {
						
					try { 
						FileInputStream fileau = new FileInputStream("src\\video\\901032.wav"); 
						AudioStream as = new AudioStream(fileau); 
						AudioPlayer.player.start(as); 
						} 
						catch (IOException ie) { } 
                    //JOptionPane.showMessageDialog(null,"温度高于设定阈值",  "报警",JOptionPane.INFORMATION_MESSAGE); 
					alarmJ.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯red.png")));
				}else{
					alarmJ.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
				}
				
				if (Float.parseFloat(co2AlarmLow.getText())>Float.parseFloat(NodeJPanel.node_SET.get(node-1).co2_jTextField.getText())
						||Float.parseFloat(co2AlarmHigh.getText())<Float.parseFloat(NodeJPanel.node_SET.get(node-1).co2_jTextField.getText()) 
						) {
					
					try { 
						FileInputStream fileau = new FileInputStream("src\\video\\901032.wav"); 
						AudioStream as = new AudioStream(fileau); 
						AudioPlayer.player.start(as); 
					} 
					catch (IOException ie) { } 
					//JOptionPane.showMessageDialog(null,"二氧化碳高于设定阈值",  "报警",JOptionPane.INFORMATION_MESSAGE); 
					alarmJ2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯red.png")));
				}else{
					alarmJ2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
				}
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	class Set extends Thread{
		public void run() {
			while(true){
				if (Float.parseFloat(temp.getText())<Float.parseFloat(NodeJPanel.node_SET.get(node-1).temp_jTextField.getText())) {
					//温度上限
					PCAsServer.relayControl(node,(char)0x00,(char)0x02,(char)0x02);
					NodeJPanel.node_SET.get(node-1).tempstate_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));

				}
				if(Float.parseFloat(humi.getText())<Float.parseFloat(NodeJPanel.node_SET.get(node-1).humi_jTextField.getText())){
					PCAsServer.relayControl(node,(char)0x02,(char)0x02,(char)0x00);
					NodeJPanel.node_SET.get(node-1).feng_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));

				}
				if(Float.parseFloat(co2.getText())<Float.parseFloat(NodeJPanel.node_SET.get(node-1).co2_jTextField.getText())){
					PCAsServer.relayControl(node,(char)0x02,(char)0x00,(char)0x02);
					NodeJPanel.node_SET.get(node-1).co2valve_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
				}
				if(Float.parseFloat(temp_2.getText())>Float.parseFloat(NodeJPanel.node_SET.get(node-1).temp_jTextField.getText())){
					PCAsServer.relayControl(node,(char)0x01,(char)0x02,(char)0x02);	
					NodeJPanel.node_SET.get(node-1).tempstate_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯open.png")));

				}
				if(Float.parseFloat(humi_2.getText())>Float.parseFloat(NodeJPanel.node_SET.get(node-1).humi_jTextField.getText())){
					PCAsServer.relayControl(node,(char)0x02,(char)0x02,(char)0x01);
					NodeJPanel.node_SET.get(node-1).feng_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯open.png")));
				}
				if(Float.parseFloat(co2_2.getText())>Float.parseFloat(NodeJPanel.node_SET.get(node-1).co2_jTextField.getText())){
					PCAsServer.relayControl(node,(char)0x02,(char)0x01,(char)0x02);
					NodeJPanel.node_SET.get(node-1).co2valve_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯open.png")));
				}
//				PCAsServer.relayControl(1,(char)0x00,(char)0x02,(char)0x02);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				PCAsServer.relayControl(1,(char)0x01,(char)0x02,(char)0x02);	
			}
		}
	}
	
}
