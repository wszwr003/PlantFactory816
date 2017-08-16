package com.plant.frame;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;

import com.plant.server.PCAsServer;
import com.plant.util.SerialConnect;



/**
 * 实现ActionListener类
 * @author MR.WANG
 *
 */
public class MyAction {
	public static SerialConnect sc;
	public static Socket  upper;
	public static PrintWriter pw;
	public static BufferedReader br;	
	public static ExitAction EXIT;
	public static SocketAction CLIENT;
	public static CommAction COM;
	public static ServerSocketAction SERVER;
	static {
		EXIT = new ExitAction();
		CLIENT = new SocketAction();
		COM = new CommAction();
		SERVER = new ServerSocketAction();
	}
	
	private MyAction() {    //为什么没有这个构造函数就不行？？？？？
		super();
	}
	private static class ExitAction extends AbstractAction { // 退出系统动作
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}
	private static class SocketAction extends AbstractAction { // socket as client
		public void actionPerformed(final ActionEvent e) {
			try {
				upper  = new Socket("localhost",80);
				br = new BufferedReader( new InputStreamReader( upper.getInputStream())) ;
		        pw = new PrintWriter(upper.getOutputStream(),true);
		        pw.println("00");
		        
		        pw.println("123");
				new Thread() {
		            public void run() {
		            	String line;
		                try {
		                    while(true) {
		                        line = br.readLine();
		                        System.out.println(line);
		                    }
		                } catch(Exception ex) {}
		            }
		        }.start();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	private static class ServerSocketAction extends AbstractAction{ // socket as server
		public void actionPerformed(final ActionEvent e) {
			try {
				new PCAsServer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		
	}
	private static class CommAction extends AbstractAction { // socket as server
		public void actionPerformed(final ActionEvent e) {
			sc = new SerialConnect("COM3",115200,8,1,0);
			sc.openSerialPort();
		}
	}
	
}
