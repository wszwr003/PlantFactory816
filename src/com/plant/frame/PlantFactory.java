package com.plant.frame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.plant.server.PCAsServer;

public class PlantFactory {
	static MainFrame mf ;
	PCAsServer pcs ;
	public static void main(String[] args) throws Exception{
		mf = new MainFrame();
		new LoginFrame(mf);
		new PCAsServer();
	}
}
 