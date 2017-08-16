package com.plant.frame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VideoJPanel extends JPanel implements Runnable{
//	 boolean flag =true;
	 String node;
	 public VideoJPanel(String node){
//	 this.setSize(500, 400);
	 this.setBorder(BorderFactory.createEtchedBorder());
	 this.node =node;
	 }
	 @Override//这个方法是用来画Panel组件的
	 protected void paintComponent(Graphics g) {
		 Graphics2D g2 = (Graphics2D)g;  //g是Graphics对象
			try {
//				if(flag) {
//				BufferedImage img=ImageIO.read(new File("src\\video\\camera.jpg"));
////				BufferedImage img=ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\back.jpg"));
////				BufferedImage img=ImageIO.read(new File("images/plant.jpg"));
////				BufferedImage img=ImageIO.read(new File(this.getClass().getResource("images/plant.jpg").getPath()));
//				g2.drawImage(img, 0, 0, 320, 240,null);
//				flag =false;
//				}else {
					BufferedImage img=ImageIO.read(new File("src\\video\\camera"+node+".jpg"));
//					BufferedImage img=ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\back.jpg"));
//					BufferedImage img=ImageIO.read(new File("images/plant.jpg"));
//					BufferedImage img=ImageIO.read(new File(this.getClass().getResource("images/plant.jpg").getPath()));
					g2.drawImage(img, 0, 0, 320, 240,null);
//				}
			} catch (IOException e) {
//				try {
//					BufferedImage img = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\back.jpg"));
//					g2.drawImage(img, 0, 0, 320, 240,null);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
			}
	}
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint(0, 0, 320, 240);
		}
	}
}
//public class VideoJPanel extends JPanel implements Runnable{
//	boolean flag =true;
//	String node;
//	public VideoJPanel(String node){
////	  this.setSize(500, 400);
//		this.setBorder(BorderFactory.createEtchedBorder());
//		this.node =node;
//	}
//	@Override//这个方法是用来画Panel组件的
//	protected void paintComponent(Graphics g) {
//		Graphics2D g2 = (Graphics2D)g;  //g是Graphics对象
//		if(flag){
//			try {
////			   BufferedImage img=ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\camera"+node+".jpg"));
//				BufferedImage img=ImageIO.read(new File("images/plant.jpg"));
//				//BufferedImage img=ImageIO.read(new File(this.getClass().getResource("images/plant.jpg").getPath()));
//				g2.drawImage(img, 0, 0, 320, 240,null);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}else{
//			try {
//				BufferedImage img=ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\camera"+node+".jpg"));
////				BufferedImage img=ImageIO.read(new File("images/plant.jpg"));
//				//BufferedImage img=ImageIO.read(new File(this.getClass().getResource("images/plant.jpg").getPath()));
//				g2.drawImage(img, 0, 0, 320, 240,null);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//	@Override
//	public void run() {
//		while (true) {
//			flag =true;
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			repaint(0, 0, 320, 240);
//			flag =false;
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			repaint(0, 0, 320, 240);
//		}
//		
//	}
//}
//public class VideoJPanel extends JPanel {
//	private static JLabel pump = new JLabel();
//	
//	public VideoJPanel() {
//		this.setLayout(null);
//		this.setBounds(0, 0, 300, 300);
//		pump.setBounds(0, 0, 50, 80);
//		pump.setText("<html><img src='file:///C:/Users/Administrator/Desktop/micro/pumpyou.png' /></html>");
//		this.add(pump);
//		repaint();
//	}
//	
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D)g;
//	}
//}
