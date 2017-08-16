package com.plant.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.plant.server.PCAsServer;

public class LoginFrame extends JFrame{
	public static String look = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
	public LoginFrame(MainFrame mf) {
		
		try {
			UIManager.setLookAndFeel(look);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(500	, 330));
		setUndecorated(true);
		setSize(500, 330);
		setLocationRelativeTo(null);			//locate in center
		setLayout(null);
		
		MyJPanel jpb = new MyJPanel(new ImageIcon("src\\images\\植物工廠01.png").getImage());
		jpb.setOpaque(false);
		jpb.setForeground(Color.WHITE);
		JLabel jb =new JLabel("植物生长环境控制系统");
		JLabel jb1 = new JLabel("上海离草科技有限公司");
		//JLabel jb2 = new JLabel(new ImageIcon("src\\images\\logo2.png"));
		jb.setFont(new Font("楷体", Font.BOLD, 35));
		jb1.setFont(new Font("楷体", Font.BOLD, 21));
		jb.setForeground(Color.white);
		jb1.setForeground(new Color(23, 132, 59));
		jpb.setBounds(0, 0, getWidth(), getHeight());
		jb.setBounds(70,60, 400, 50);
		jb1.setBounds(280, 0, 400, 50);
		//jb2.setBounds(200, 0, 54, 54);
		
		add(jb);
		add(jb1);
		//add(jb2);
		String count[] ={"wszwr003","licaokeji001"};
		JComboBox jcb = new JComboBox(count);
		jcb.setBounds(160, 140, 160, 30);
		add(jcb);
		JPasswordField jf = new JPasswordField("123456");
		jf.setBounds(160, 173, 160, 30);
		add(jf);
		
		JButton jbutton1 = new JButton("登陆");
		jbutton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(((String)jcb.getSelectedItem()).equals("wszwr003")&&jf.getText().equals("123456")){
					mf.setVisible(true);    	 
					dispose();
				}else{
					JOptionPane.showMessageDialog(null,"账号或密码错误", "错误",  JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		jbutton1.setBounds(120, 220, 80, 40);
		add(jbutton1);
		JButton jbutton2 = new JButton("关闭");
		jbutton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				System.exit(0);
				//setDefaultCloseOperation(EXIT_ON_CLOSE);
				
			}
		});
		jbutton2.setBounds(270, 220, 80, 40);
		add(jbutton2);
		
		add(jpb);
	
		
		
		
		setVisible(true);
        

	}
	public static void main(String[] args) {
		//new LoginFrame();
	}
}
