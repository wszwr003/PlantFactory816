package com.plant.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.TooManyListenersException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


//import com.lemei.FirstJFrame;
//import com.lemei.instruction.MyInstruction;

public class SerialConnect extends JFrame implements ActionListener, SerialPortEventListener{
	
	private String portname;
	private int rate, data, stop, parity; 
	
    protected SerialPort serialPort;  
    protected CommPortIdentifier portId; 
//    protected String mesg;
    protected OutputStream outputStream = null;   
    protected InputStream inputStream = null;  
    public byte[] readBuffer = new byte[10000] ;
    public boolean readFlag ;
    public static volatile Boolean isReceive = false;
   // public static byte[] dataReceive;
    public static volatile String dataReceive = "0";
    public static volatile double[] dataFloat = new double[9];
   // public static volatile double OD_V=1.79;
	public SerialConnect(String portname,int rate,int data,int stop,int parity) {
		this.portname = portname;
		this.rate = rate;
		this.data = data;
		this.stop = stop;
		this.parity = parity;
	}
	
	/** 
     * �򿪴��ж˿� 
     * @since 2012-3-23 ����12:03:07 
     */  
    public void openSerialPort() {   
        // ��ȡҪ�򿪵Ķ˿�  
        try {  
            portId = CommPortIdentifier.getPortIdentifier(portname);  
        } catch (NoSuchPortException e) {  
            showErrMesgbox("��Ǹ,û���ҵ�"+portname+"���ж˿ں�!");  
            return ;  
        }  
        // �򿪶˿�  
        try {  
            serialPort = (SerialPort) portId.open(portname, 2000); 
//            FirstJFrame.jLabelRS232.setIcon(new ImageIcon(FirstJFrame.class.getResource("/ָʾ��open.png")));
            showErrMesgbox(portname+"�˿ڳɹ���!");
        } catch (PortInUseException e) {  
            showErrMesgbox(portname+"�˿��ѱ�ռ��,����!");  
            return ;  
        }  
          
        // ���ö˿ڲ���  
        try {  
            serialPort.setSerialPortParams(rate,data,stop,parity);  
        } catch (UnsupportedCommOperationException e) {  
            showErrMesgbox(e.getMessage());  
        }  
  
        // �򿪶˿ڵ�IO���ܵ�   
        try {   
            outputStream = serialPort.getOutputStream();   
            inputStream = serialPort.getInputStream();   
        } catch (IOException e) {  
            showErrMesgbox(e.getMessage());  
        }   
  
        // ���˿���Ӽ�����  
        try {   
            serialPort.addEventListener(this);   
        } catch (TooManyListenersException e) {  
            showErrMesgbox(e.getMessage());  
        }   
        serialPort.notifyOnDataAvailable(true);   
    }  
    
    /** 
     * �رմ��ж˿� 
     * @since 2012-3-23 ����12:05:28 
     */  
    public void closeSerialPort() {   
    	try {   
    		if(outputStream != null)  
    			outputStream.close();  
    		if(serialPort != null)  
    			serialPort.close();   
    		serialPort = null; 
//    		inputStream.close();
    		showErrMesgbox(portname+"�˿ڳɹ��ر�!");
    	} catch (Exception e) {   
    		showErrMesgbox(e.getMessage());  
    	}   
    }     
      
    /** 
     * �����ж˿ڷ������� 
     * @since 2012-3-23 ����12:05:00 
     */  
    public void sendDataToSeriaPort(String mesg) {   
        try {   
            outputStream.write(mesg.getBytes());   
            outputStream.flush();   
           
        } catch (IOException e) {   
            showErrMesgbox(e.getMessage());  
        }   
    }  
    
    /** 
     * �����ж˿ڷ������� 
     * @since 2012-3-23 ����12:05:00 
     */  
    public void sendDataToSeriaPort(byte[] mesg) {   
        try {   
            outputStream.write(mesg,0,mesg.length);   
            outputStream.flush();
            
            Thread.sleep(20);
        } catch (IOException e) {   
            showErrMesgbox(e.getMessage());  
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    } 
      
      
    /** 
     * ��ʾ����򾯸���Ϣ 
     * @param msg ��Ϣ 
     * @since 2012-3-23 ����12:05:47 
     */  
    public void showErrMesgbox(String msg) {  
        JOptionPane.showMessageDialog(this, msg);  
    }

	public void actionPerformed(ActionEvent arg0) {
		
	}

	public void serialEvent(SerialPortEvent arg0) {
		switch (arg0.getEventType()) {  
        case SerialPortEvent.BI:  
        case SerialPortEvent.OE:  
        case SerialPortEvent.FE:  
        case SerialPortEvent.PE:  
        case SerialPortEvent.CD:  
        case SerialPortEvent.CTS:  
        case SerialPortEvent.DSR:  
        case SerialPortEvent.RI:  
        case SerialPortEvent.OUTPUT_BUFFER_EMPTY:  
            break;  
        case SerialPortEvent.DATA_AVAILABLE:  
        	
            try{Thread.sleep(1000);}catch (InterruptedException e){e.printStackTrace();}
            try
            {
              while (this.inputStream.available() > 0) {
            	  	  int temp =this.inputStream.available();
            		  this.inputStream.read(readBuffer);
                      }

        		  ImageInput.byte2image(readBuffer, "C:\\Users\\Administrator\\Desktop\\cameranode1.jpg");
            	  
            }
            catch (IOException e)
            {
              showErrMesgbox(e.getMessage());
            }
//            try{Thread.sleep(1000);}catch (InterruptedException e){e.printStackTrace();}
//            try
//            {
//              while (this.inputStream.available() > 0) {
//            	  	  int temp =this.inputStream.available();
//            		  readBuffer2 = new byte[temp];	
//            		  this.inputStream.read(readBuffer2);	
//            		  readFlag = true;
//            		  if ((readBuffer[0] == 0xFF) && (readBuffer[1] == 0xD8))
//                      {
//            		  readBuffer1 = new byte[temp];	
//            		  readBuffer1 =  readBuffer2;
//            		  readFlag = false;
//                      }
//                
//              }
//             //System.out.println(Arrays.toString(readBuffer));
//              if (!readFlag) {
//        		  ;	
//        	  }else{
//        		  readBuffer = ImageInput.byteMerger(readBuffer1, readBuffer2);
//        		  ImageInput.byte2image(readBuffer, "C:\\Users\\Administrator\\Desktop\\cameranode1.jpg");
//        	  }
//            	  
//            }
//            catch (IOException e)
//            {
//              showErrMesgbox(e.getMessage());
//            }
    }  
		
	}

}
