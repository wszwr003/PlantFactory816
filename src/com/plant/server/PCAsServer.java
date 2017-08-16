package com.plant.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


import com.plant.frame.MainFrame;
import com.plant.frame.NodeJPanel;
import com.plant.util.ImageInput;
import com.plant.util.TimerMinute;


public class PCAsServer  {
	public static byte[] zhen = new byte[60000];
	public static final byte[] frame_HEAD = { 0x26, 0x21 };
	public static final byte[] frame_ADDRESS_PC = {0x00};
	public static final byte[] frame_ADDRESS_Node_1 = { 0x01 };
	public static final byte[] frame_ADDRESS_Node_2 = { 0x02 };
	public static final byte[] frame_ADDRESS_Node_3 = { 0x03 };
	public static final byte[] frame_ADDRESS_Node_4 = { 0x04 };
	public static final byte[] frame_ADDRESS_Node_5 = { 0x05 };
	public static final byte[] frame_ADDRESS_Node_6 = { 0x06 };
	public static final byte[] frame_KIND_1 = { 0x01 };
	public static final byte[] frame_KIND_2 = { 0x02 };
	public static final byte[] frame_KIND_3 = { 0x03 };
	public static final byte[] frame_KIND_4 = { 0x04 };
	public static byte[] frame_DATALENGTH = new byte[1];
	public static byte[] frame_DATA;
	public static byte[] frame_CRC16 = new byte[2];
	public static final byte[] frame_TALE = { 0x7C, 0x23 };
	  
	public static final byte[] GET_AND_ON = { 0x01 };
	public static final byte[] NOGET_AND_OFF = { 0x00 };
	public static final byte[] lazer_ON = { 1 };
	public static final byte[] lazer_OFF = { 0 };

	public static  char[] zhen_GET = { 0x26,0x21,0x00,0x01,0x02,0x04,0x01,0x01,0x01,0x01,0xFF,0xFF,0x7c,0x23 };
	public static  char[] zhen_RelayOn = { 0x26,0x21,0x00,0x01,0x03,0x03,0x01,0x01,0x01,0xFF,0xFF,0x7c,0x23 };
	public static  char[] zhen_RelayOFF = { 0x26,0x21,0x00,0x01,0x03,0x03,0x00,0x00,0x00,0xFF,0xFF,0x7c,0x23 };
	public static  char[] zhen_Conn = { 0x26,0x21,0x00,0x01,0x05,0x03,0xff,0xff,0xff,0xFF,0xFF,0x7c,0x23 };
	public static  char[] zhen_Conn2 = { 0x26,0x21,0x00,0x02,0x05,0x03,0xff,0xff,0xff,0xFF,0xFF,0x7c,0x23 };
	public static  char[] zhen_Video = { 0x26,0x21,0x00,0x01,0x04,0x00,0xff,0xff,0x7c,0x23 };
	public static  char[] zhen_Video2 = { 0x26,0x21,0x00,0x01,0x07,0x00,0xff,0xff,0x7c,0x23 };
	public static  char[] zhen_Data = { 0x26,0x21,0x00,0x01,0x02,0x04,0xff,0xff,0xff,0xff,0xff,0xff,0x7c,0x23 };
	public static  char[] zhen_Data2 = { 0x26,0x21,0x00,0x01,0x08,0x04,0xff,0xff,0xff,0xff,0xff,0xff,0x7c,0x23 };
	int i=0;
	public ServerSocket server;
	public Socket client;
//	public Vector<ManageClient> clients = new Vector<ManageClient>();

	public static Map<Integer, ManageClient> clients = new HashMap<Integer, ManageClient>();
	public PCAsServer() throws Exception{
		
		server = new ServerSocket(8899, 10);
		server.setReceiveBufferSize(60000);
		client = null;
//		new ImageInput();
		System.out.println("PlantFactory is running!");
		//new GetData().start();
		while (true) {
			System.out.println("PlantFactory is running!");
			client = server.accept();
            ManageClient c = new ManageClient(client);
            c.start();

		}
	}
	
    public static void sendtothread(int threadname, String message,char[] mesg) {
        for (Map.Entry<Integer, ManageClient> entry : clients.entrySet()) {  
        	  if (entry.getKey().equals(threadname)) {
              //entry.getValue().output.println(message);
        	  //System.out.println(mesg[0]+mesg[1]);
               //entry.getValue().output.println(Arrays.toString(mesg));
               //entry.getValue().output.println("321123");
               //System.out.println(message);
        		  entry.getValue().output.write(mesg);
        		  entry.getValue().output.flush();
        	  }
        }  
    }
    
    public static void relayControl(int node,char realy1,char realy2,char realy3) {
    	char[] temp = zhen_RelayOn;
    	temp[3] = (char)node;
    	temp[6] = realy1;
    	temp[7] = realy2;
    	temp[8] = realy3;
    	sendtothread(node,"",temp);
    }
    
    public static int returnAddr(ManageClient thread) {
    	int i = 0;
        for (Map.Entry<Integer, ManageClient> entry : clients.entrySet()) {  
        	  if (entry.getValue().equals(thread)) {
        		  i = entry.getKey();
        	  }
        }
		return i;  
    }
    
    
    public double[] receiveFrame(byte[] frame)
    {
  	  
  			double[] temp = new double[5];
  			int[] a = new int[frame.length];

  			for (int i = 0; i < frame.length; i++) {
  				a[i]= frame[i] & 0xff;
  			}
  				temp[0] = a[2];     //节点编号
  				temp[1] = ((double)(a[6]))*256+((double)(a[7]));
  				temp[2] = ((double)(a[8]))*256+((double)(a[9]));
  				temp[3] = ((double)(a[10]))*256+((double)(a[11]));
  				temp[4] = ((double)(a[12]))*256+((double)(a[13]));
  				temp[1] = temp[1]*0.1;
  				temp[2] = temp[2]*0.1;
  				temp[3] = (temp[3]/1.2)*15.322/1000;
  				temp[4] = temp[4];
  			
  			  //设置保留位数
  			BigDecimal   bd0  =   new  BigDecimal(temp[1]);    
  			bd0   =  bd0.setScale(1,4);    
  			temp[1]   =  bd0.doubleValue();  
  			
  			BigDecimal   bd1  =   new  BigDecimal(temp[2]);    
  			bd1   =  bd1.setScale(1,4);    
  			temp[2]   =  bd1.doubleValue();  
  			
  			BigDecimal   bd2  =   new  BigDecimal(temp[3]);    
  			bd2   =  bd2.setScale(1,4);    
  			temp[3]   =  bd2.doubleValue();  
  			
  			BigDecimal   bd3  =   new  BigDecimal(temp[4]);    
  			bd3   =  bd3.setScale(1,4);    
  			temp[4]   =  bd3.doubleValue();  
  			
  			return temp;
    }

	
	class ManageClient extends Thread {

	        private Socket client;
	    	public byte[] readBuffer=new byte[60000];
			String gotthread = "";
	        InputStream input;
	        PrintWriter output;
        	boolean video = false;
	        TimerMinute tm = new TimerMinute();
	        int node;
	        byte[] addr = new byte[20];
	        public synchronized void start(Socket client) {
	        super.start();
	        }
	        public ManageClient(Socket client) throws IOException {
	        	    
	        	    this.client = client;
	            	input = client.getInputStream();
					output = new PrintWriter(client.getOutputStream(), true);
		            while ((input.read(addr,0,10))!=-1){
		            	if (addr[0]=='&'&&addr[1]=='!'&&addr[4]==0x00&&addr[8]=='|'&&addr[9]=='#') {
			            	clients.put((int)addr[2],this);     //添加到hashmap
			            	NodeJPanel.isConn[(int)addr[2]] = true;
			            	System.out.println(NodeJPanel.isConn);
			            	System.out.println("addr="+addr[2]);
			            	zhen_Conn[3]=(char)addr[2];
			            	PCAsServer.sendtothread(addr[2],"asd", PCAsServer.zhen_Conn);
			            	PCAsServer.sendtothread(addr[2],"asd", PCAsServer.zhen_Conn);
			            	PCAsServer.sendtothread(addr[2],"asd", PCAsServer.zhen_Conn);
			            	node=addr[2];
			            	break;
		            	}
		            }
	        }

	        
	        public void run() {
//	        	boolean video = false;
	            double[]  buffer = new double[4];
//	            byte[] bt ;
	            
	            tm.startGetVideo(node,buffer,client);
	            tm.startTimerBeat(node,buffer,client,input,output);
	            tm.startTimerStoreData(node,buffer,client);
	            tm.startTimerDisplayData(node,buffer,client);
	            try {  	
	            	int zhenshu= 0;
//	            	while(true) {
//	            		Thread.sleep(5000);
//	            		clients.remove(node);
//                		input.close();
//        			    output.close();
//        			    client.close();
//	            	}
	                while (true) {
	                	if (input.available()<=0) {		
							continue;
						}else if (video ==false){
							readBuffer = new byte[20];
							input.read(readBuffer);
						}else if (video ==true){
//							Thread.sleep(15000);
//							input.read(readBuffer,0,zhenshu+13);
							if(i==zhenshu/1024) {
								input.read(readBuffer,i*1024,zhenshu%1024);
								Thread.sleep(50);
								i=0;
							}else {
								System.out.println("test node"+node);
								input.read(readBuffer,i*1024, 1024);
								//System.out.println(Arrays.toString(readBuffer));
								Thread.sleep(50);
								zhen_Video2[3] = (char)node;
								PCAsServer.sendtothread(node,"", PCAsServer.zhen_Video2);
								i++;
								System.out.println(i);
								continue;
							}
							System.out.println(readBuffer[0]);System.out.println(readBuffer[1]);
							System.out.println(readBuffer[zhenshu-2]);System.out.println(readBuffer[zhenshu-1]);
						}
	                	      		
	                	if (readBuffer[0]=='&'&&readBuffer[1]=='!'&&readBuffer[4]==0x01&&readBuffer[16]=='|'&&readBuffer[17]=='#') {
				       	                buffer = receiveFrame(readBuffer);
				       	             	NodeJPanel.node_SET.get((int)buffer[0]-1).temp_jTextField.setText(String.valueOf(buffer[1]));
				       	             	NodeJPanel.node_SET.get((int)buffer[0]-1).humi_jTextField.setText(String.valueOf(buffer[2]));
				       	             	NodeJPanel.node_SET.get((int)buffer[0]-1).light_jTextField.setText(String.valueOf(buffer[3]));
				       	             	NodeJPanel.node_SET.get((int)buffer[0]-1).co2_jTextField.setText(String.valueOf(buffer[4]));
				       	                System.out.println(Arrays.toString(readBuffer));
										//PCAsServer.sendtothread(node,"", PCAsServer.zhen_Data2);			
		                }
	                	else if(readBuffer[0]=='&'&&readBuffer[1]=='!'&&readBuffer[4]==0x09&&readBuffer[8]=='|'&&readBuffer[9]=='#') {
	                		tm.timebeat = 0;
	                	}
	                	else if(readBuffer[0]==0x55&&readBuffer[1]==0x48) {
		                			    zhenshu = (readBuffer[8]&0xff)*256+(readBuffer[7]&0xff);
		    							readBuffer = new byte[60000];
		    							Thread.sleep(10);
		                			    video =true;
		                			    i=0;
//		                			    System.out.println(Arrays.toString(readBuffer));
		                			    System.out.println(zhenshu);
		                }
	                	else if(readBuffer[0]==-1&&readBuffer[1]==-40) {
	                		System.out.println("ASDASD");
	                		video =false;
            			    byte[] zhen = new byte[zhenshu];
            			    for (int n= 0; n < zhenshu; n++) {
            			    	zhen[n]=readBuffer[n];
							}
            			   
            			    //System.out.println(Arrays.toString(readBuffer));
//            			    System.out.println(zhen[0]);System.out.println(zhen[0]);
//            			    System.out.println(zhen[zhenshu-2]);System.out.println(zhen[zhenshu-1]);
							new PictureCov(zhen,"src\\video\\cameranode"+returnAddr(this)+".jpg").start();
							if (NodeJPanel.videobutton) {
								new PictureCov(zhen,"picture\\takenode"+returnAddr(this)+"\\cameranode"+TimerMinute.getCurrentTime2()+".jpg").start();
								NodeJPanel.videobutton = false;
							}else 
							{
								new PictureCov(zhen,"picture\\node"+returnAddr(this)+"\\cameranode"+TimerMinute.getCurrentTime2()+".jpg").start();
							}
							for (int n= 0; n < 60000; n++) {
									readBuffer[n]=0;
							}            			    
							zhenshu = 0;

//							PCAsServer.sendtothread(node,"", PCAsServer.zhen_Video2);
						}
//	                	else if(readBuffer[0]=='&'&&readBuffer[1]=='!'&&readBuffer[4]==0x06) {
//            			    zhenshuNow = readBuffer[5]*1000+readBuffer[6]*10+readBuffer[7];
////							int[] temp=new int[20];
////							for (int j = 0; j < temp.length; j++) {
////								temp[j]=readBuffer[j]&0xff;
////							}
////            			    System.out.println(Arrays.toString(temp));
//            			    System.out.println("video datas："+zhenshuNow);
//            			    video =false;
//            			    byte[] zhen = new byte[zhenshuNow];
//            			    for (int n= 8; n < zhenshuNow+8; n++) {
//            			    	zhen[n-8]=readBuffer[n];
//								//readBuffer[n]=0;
//							}
//            			    
//							new PictureCov(zhen,"src\\video\\cameranode"+returnAddr(this)+".jpg").start();
//							if (NodeJPanel.videobutton) {
//								new PictureCov(zhen,"picture\\takenode"+returnAddr(this)+"\\cameranode"+TimerMinute.getCurrentTime2()+".jpg").start();
//								NodeJPanel.videobutton = false;
//							}else 
//							{
//								new PictureCov(zhen,"picture\\node"+returnAddr(this)+"\\cameranode"+TimerMinute.getCurrentTime2()+".jpg").start();
//							}
////							PCAsServer.sendtothread(node,"", PCAsServer.zhen_Video2);
//						}
//	                	else if(readBuffer[0]=='&'&&readBuffer[1]=='!'&&readBuffer[4]==0x06) {
//            			    zhenshuNow = readBuffer[5];
//            			    for (int n= 6; n < 1006; n++) {
//								readBufferV[zhenshuNow][n-6]=readBuffer[n];
//								readBuffer[n]=0;
//							}
//                			if (zhenshuNow == zhenshu-1) {
//                				byte[] zhen = new byte[zhenshu*1000];
//								for (int m = 0; m < zhenshu; m++) {
//									for (int n= 0; n < 1000; n++) {
//										zhen[m*1000+n]=readBufferV[m][n];
//									}
//									//System.out.println(Arrays.toString(readBufferV[m]));
//								}
//								new PictureCov(zhen,"src\\video\\cameranode"+returnAddr(this)+".jpg").start();
//								if (NodeJPanel.videobutton) {
//									new PictureCov(zhen,"picture\\takenode"+returnAddr(this)+"\\cameranode"+TimerMinute.getCurrentTime2()+".jpg").start();
//									NodeJPanel.videobutton = false;
//								}else 
//								{
//									new PictureCov(zhen,"picture\\node"+returnAddr(this)+"\\cameranode"+TimerMinute.getCurrentTime2()+".jpg").start();
//								}
//							}
//                      }
	                }
	            } 
	            catch (Exception ex) {
	            	System.out.println(ex.getMessage());
	            }
	        }
	    } 
	
	class GetData extends Thread{
		@Override
		public void run() {
			while(true){
				PCAsServer.sendtothread(1,"asd", PCAsServer.zhen_GET);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class PictureCov extends Thread{
		byte[] zhen;
		String root;
		public PictureCov(byte[] zhen,String root) {
			this.zhen = zhen;
			this.root = root;
		}
		public void run() {
			ImageInput.byte2image(zhen, root);
		}
	}
}