package com.plant.frame;


import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SetAllTab extends JPanel{
	public SetAllTab() {
		  JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP); 
		  //容器
		  Container container = this;
		  //对象化面板
		  JPanel combop = new JPanel();
//		  JPanel p1 = new JPanel();
//		  JPanel p2 = new JPanel();
//		  JPanel p3 = new JPanel();
//		  JPanel p4 = new JPanel();
//		  JPanel p5 = new JPanel();
//		  JPanel p6 = new JPanel();

		  SetSingleTab p1 = new SetSingleTab(1);
		  SetSingleTab p2 = new SetSingleTab(2);
		  SetSingleTab p3 = new SetSingleTab(3);
		  SetSingleTab p4 = new SetSingleTab(4);
		  SetSingleTab p5 = new SetSingleTab(5);
		  SetSingleTab p6 = new SetSingleTab(6);
		  
		  tab.add(p1,"NODE 1");
		  tab.add(p2,"NODE 2");
		  tab.add(p3,"NODE 3");
		  tab.add(p4,"NODE 4");
		  tab.add(p5,"NODE 5");
		  tab.add(p6,"NODE 6");
		  
		  //combop.add(new JLabel("学生信息管理系统"));
		  //container.add(combop,BorderLayout.NORTH);
		  
		  container.setLayout(new BorderLayout());
		  container.add(tab,BorderLayout.CENTER);
	}
}
