package com.plant.frame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MyMenu extends JMenuBar{
	public MyMenu() {
		
		JMenu jMenuFile = new JMenu("文件");
		JMenu jMenuEdit = new JMenu("编辑");
		JMenu jMenuSet = new JMenu("设置");
		JMenu jMenuWindow = new JMenu("窗口");
		JMenu jMenuDebug = new JMenu("调试");
		JMenu jMenuHelp = new JMenu("帮助");
		
		this.add(jMenuFile);
		this.add(jMenuEdit);
		this.add(jMenuSet);
		this.add(jMenuWindow);
		this.add(jMenuDebug);
		this.add(jMenuHelp);
	
//		JMenuItem jMenuItemExit = new JMenuItem("退出(E)",new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/logo2020.png"))));
//		jMenuItemExit.setAction(MyAction.EXIT);  //此种方式不行一定要先定义action再定义text和icon？
		JMenuItem jMenuItemNew = new JMenuItem(MyAction.EXIT);
		jMenuItemNew.setText("新建(N)");
		jMenuItemNew.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/红灯2020.png"))));
		jMenuItemNew.setMnemonic(KeyEvent.VK_N);
		jMenuItemNew.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_N, ActionEvent.ALT_MASK));
		jMenuItemNew.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		
		JMenuItem jMenuItemOpen = new JMenuItem(MyAction.EXIT);
		jMenuItemOpen.setText("打开(O)");
		jMenuItemOpen.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/红灯2020.png"))));
		jMenuItemOpen.setMnemonic(KeyEvent.VK_O);
		jMenuItemOpen.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, ActionEvent.ALT_MASK));
		jMenuItemOpen.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		
		JMenuItem jMenuItemExit = new JMenuItem(MyAction.EXIT);
		jMenuItemExit.setText("退出(E)");
		jMenuItemExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/红灯2020.png"))));
		jMenuItemExit.setMnemonic(KeyEvent.VK_E);
		jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_E, ActionEvent.ALT_MASK));
		jMenuItemExit.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		
		jMenuFile.add(jMenuItemNew);
		jMenuFile.add(jMenuItemOpen);
		jMenuFile.addSeparator();
		jMenuFile.add(jMenuItemExit);
		
		JMenuItem jMenuItemConn = new JMenuItem(MyAction.COM);
		jMenuItemConn.setText("串口连接(R)");
		jMenuItemConn.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/红灯2020.png"))));
		jMenuItemConn.setMnemonic(KeyEvent.VK_R);
		jMenuItemConn.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_R, ActionEvent.ALT_MASK));
		jMenuItemConn.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		
		JMenuItem jMenuItemClient = new JMenuItem(MyAction.CLIENT);
		jMenuItemClient.setText("连接到服务器(C)");
		jMenuItemClient.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/红灯2020.png"))));
		jMenuItemClient.setMnemonic(KeyEvent.VK_C);
		jMenuItemClient.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_C, ActionEvent.ALT_MASK));
		jMenuItemClient.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		
		JMenuItem jMenuItemServer = new JMenuItem(MyAction.SERVER);
		jMenuItemServer.setText("等待节点连接(S)");
		jMenuItemServer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/红灯2020.png"))));
		jMenuItemServer.setMnemonic(KeyEvent.VK_S);
		jMenuItemServer.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.ALT_MASK));
		jMenuItemServer.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		
		jMenuSet.add(jMenuItemConn);
		jMenuSet.add(jMenuItemClient);
		jMenuSet.add(jMenuItemServer);
	}
}
/**
 * 原下代码为原frame直接实现
 */
//private void initMenu() {
//JMenuBar jMenuBar = new JMenuBar();
//this.setJMenuBar(jMenuBar);
//
//JMenu jMenuFile = new JMenu("文件");
//JMenu jMenuEdit = new JMenu("编辑");
//JMenu jMenuSet = new JMenu("设置");
//JMenu jMenuWindow = new JMenu("窗口");
//JMenu jMenuDebug = new JMenu("调试");
//JMenu jMenuHelp = new JMenu("帮助");
//
//jMenuBar.add(jMenuFile);
//jMenuBar.add(jMenuEdit);
//jMenuBar.add(jMenuSet);
//jMenuBar.add(jMenuWindow);
//jMenuBar.add(jMenuDebug);
//jMenuBar.add(jMenuHelp);
//
//JMenuItem jMenuItemNew = new JMenuItem("新建(N)",new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/logo2020.png"))));
//jMenuItemNew.setMnemonic(KeyEvent.VK_N);
//jMenuItemNew.setAccelerator(KeyStroke.getKeyStroke(
//		KeyEvent.VK_N, ActionEvent.ALT_MASK));
//jMenuItemNew.getAccessibleContext().setAccessibleDescription(
//		"This doesn't really do anything");
//
//JMenuItem jMenuItemOpen = new JMenuItem("打开(O)",new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/logo2020.png"))));
//jMenuItemOpen.setMnemonic(KeyEvent.VK_O);
//jMenuItemOpen.setAccelerator(KeyStroke.getKeyStroke(
//		KeyEvent.VK_O, ActionEvent.ALT_MASK));
//jMenuItemOpen.getAccessibleContext().setAccessibleDescription(
//		"This doesn't really do anything");
//
//JMenuItem jMenuItemExit = new JMenuItem("退出(E)",new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/images/logo2020.png"))));
//jMenuItemExit.setMnemonic(KeyEvent.VK_E);
//jMenuItemExit.setAccelerator(KeyStroke.getKeyStroke(
//		KeyEvent.VK_E, ActionEvent.ALT_MASK));
//jMenuItemExit.getAccessibleContext().setAccessibleDescription(
//		"This doesn't really do anything");
//
////jMenuItemNew.addActionListener(new ActionListener() {		//如何实现全局Actionlisten
////	public void actionPerformed(ActionEvent e) {
////		
////	}
////});
////jMenuItemOpen.addActionListener(new ActionListener() {
////	public void actionPerformed(ActionEvent e) {
////		
////	}
////});
////jMenuItemExit.addActionListener(new ActionListener() {
////	public void actionPerformed(ActionEvent e) {
////		dispose();
////	}
////});
//
//jMenuFile.add(jMenuItemNew);
//jMenuFile.add(jMenuItemOpen);
//jMenuFile.addSeparator();
//jMenuFile.add(jMenuItemExit);
//}

