package com.plant.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;;

public class MyJPanel extends JPanel {
	
	   Image im;  
	    public MyJPanel(Image im)  
	    {  
	        this.im=im;  
	        this.setOpaque(false);  
	    }  
	
	protected void paintComponent(Graphics g) {
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this); 
        //g.setColor(color);
        //g.fillRect(0, 0, 500, 333);
        super.paintComponent(g);
	}
}
