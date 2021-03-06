package com.plant.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ClientInfoStatus;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

import com.plant.frame.NodeJPanel;
import com.plant.frame.PlantFactory;
import com.plant.server.PCAsServer;

public class TimerMinute {
	public int timebeat = 0;
	public boolean videoFlag = false;
	public TimerMinute() {
		System.out.println("task start:" + getCurrentTime());
	}
	public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        return sdf.format(date);
    }
	public static String getCurrentTime2() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd_HH_mm_ss_SSS");
        return sdf.format(date);
    }
	public static String getCurrentTime3() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
	public static String getCurrentTime4() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-HH-mm-ss");
        return sdf.format(date);
    }
	
    public  void startGetVideo(int node,double buffer[],Socket client){
    	SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
            	if (client.isClosed()) {
					this.cancel();
				}
            	else {
            		System.out.println("send get Video Node"+node+":" + getCurrentTime());
            		PCAsServer.zhen_Video[3]=(char)node;
            		PCAsServer.sendtothread(node,"", PCAsServer.zhen_Video);    //视频采集指令
            	}
              }
        };
        Timer timer = new Timer();
        timer.schedule(task,buildTime2(node),1000*60*5);
      //timer.schedule(task, buildTime(), 1000 * 60 * 60 * 24);
    }
    
    public  void startTimerStoreData(int node,double buffer[],Socket client){
    	String[] tmp = new String[6];
    	SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm");
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
            	if (client.isClosed()) {
					this.cancel();
				}
            	else {
	                System.out.println("StoreData Node"+node+":" + getCurrentTime());
	                tmp[0] = "1";
	                tmp[1] = df.format(Long.valueOf(System.currentTimeMillis()));
	                tmp[2] = NodeJPanel.node_SET.get(node-1).temp_jTextField.getText();
	                tmp[3] = NodeJPanel.node_SET.get(node-1).humi_jTextField.getText();
	                tmp[4] = NodeJPanel.node_SET.get(node-1).light_jTextField.getText();
	                tmp[5] = NodeJPanel.node_SET.get(node-1).co2_jTextField.getText();
	                try {//		
						NodeJPanel.node_SET.get(node-1).ePoi.writeLocalExcel("Node_"+node, tmp);
					} catch (IOException e) {
						e.printStackTrace();
					}
            	}
              }
        };
        Timer timer = new Timer();
        timer.schedule(task,buildTime(),1000*60);
      //timer.schedule(task, buildTime(), 1000 * 60 * 60 * 24);
    }
    public  void startTimerDisplayData(int node,double buffer[],Socket client){
    	String[] tmp = new String[6];
    	SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm:ss");
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
            	if (client.isClosed()) {
            		this.cancel();
            	}else if(videoFlag == true) {
            		;
            	}
            	else {
            		System.out.println("get Data Node"+node+":" + getCurrentTime());
            		PCAsServer.zhen_Data[3]=(char)node;
                	PCAsServer.sendtothread(node,"", PCAsServer.zhen_Data);    //数据采集指令
            	}
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,0,1000*2);
      //timer.schedule(task, buildTime(), 1000 * 60 * 60 * 24);
    }
    public void startTimerBeat(int node,double buffer[],Socket client,InputStream input,PrintWriter output){
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
            	if (client.isClosed()) {
            		this.cancel();
            	}
            	if (15==timebeat) {
					try {
						PCAsServer.clients.remove(node);
						input.close();
						output.close();
						client.close();
						this.cancel();
						NodeJPanel.node_SET.get((int)node-1).video_jButton.setEnabled(false);
		            	NodeJPanel.node_SET.get((int)node-1).co2_jButton.setEnabled(false);
		            	NodeJPanel.node_SET.get((int)node-1).co2_jButton2.setEnabled(false);
		            	NodeJPanel.node_SET.get((int)node-1).feng_jButton.setEnabled(false);
		            	NodeJPanel.node_SET.get((int)node-1).feng_jButton2.setEnabled(false);
		            	NodeJPanel.node_SET.get((int)node-1).temp_jButton.setEnabled(false);
		            	NodeJPanel.node_SET.get((int)node-1).temp_jButton2.setEnabled(false);
		            	
		            	NodeJPanel.node_SET.get((int)node-1).temp_jTextField.setText("0.0");
		            	NodeJPanel.node_SET.get((int)node-1).humi_jTextField.setText("0.0");
		            	NodeJPanel.node_SET.get((int)node-1).light_jTextField.setText("0.0");
		            	NodeJPanel.node_SET.get((int)node-1).co2_jTextField.setText("0.0");
		            	
						NodeJPanel.node_SET.get(node-1).tempstate_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
						NodeJPanel.node_SET.get(node-1).feng_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
						NodeJPanel.node_SET.get(node-1).co2valve_jLabel2.setIcon(new ImageIcon(PlantFactory.class.getResource("/images/指示灯black.png")));
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
            	timebeat++;
              }
        };
        Timer timer = new Timer();
        timer.schedule(task,0,1000*1);
      //timer.schedule(task, buildTime(), 1000 * 60 * 60 * 24);
    }
    
    private static Date buildTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,1);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        return time;
    }
    private static Date buildTime2(int node) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,1);
        calendar.set(Calendar.SECOND,0+15*node);
        Date time = calendar.getTime();
        return time;
    }
}
