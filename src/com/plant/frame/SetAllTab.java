package com.plant.frame;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SetAllTab extends JPanel{
	public SetAllTab() {
		//this.setSize(1366,768);
		this.setBounds(0, 0, 600, 700);
		this.setLayout(null);
		JTabbedPane jTabbedPane = new JTabbedPane();
		this.add(jTabbedPane);
		
		JPanel jPanel1 = new JPanel();
		jTabbedPane.addTab("node1",jPanel1);
		jPanel1.add(new SetPanel());
		
		JPanel jPanel2 = new JPanel();
		jTabbedPane.addTab("node2",jPanel2);
		jPanel2.add(new SetPanel());
		
		JPanel jPanel3 = new JPanel();
		jTabbedPane.addTab("node3",jPanel3);
		jPanel3.add(new SetPanel());
	}
}
