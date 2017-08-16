package com.plant.frame;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.ValueAxis;  
import org.jfree.chart.plot.XYPlot;  
import org.jfree.data.time.Millisecond;  
import org.jfree.data.time.TimeSeries;  
import org.jfree.data.time.TimeSeriesCollection;
  
public class ChartNowDataPanel4 extends ChartPanel implements Runnable
{

    private static TimeSeries timeSeries;
    private long value=0;
    private JLabel jlabel;
    private int id; 
    private String chartContent;  
    public ChartNowDataPanel4(int id,String chartContent,String title,String yaxisName)
    {
        super(createChart(chartContent,title,yaxisName));
        this.setLayout(null);
        this.id = id;
        this.chartContent = chartContent;
        jlabel = new JLabel("0");
        jlabel.setFont(new Font("dialog", Font.BOLD, 15));
        jlabel.setBounds(70, -85, 200, 200);
        this.add(jlabel);
        
    }  
      

	private static JFreeChart createChart(String chartContent,String title,String yaxisName){
        //创建时序图对象
    	timeSeries = new TimeSeries(chartContent,Millisecond.class);
       	TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
       	JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title,"时间(秒)",yaxisName,timeseriescollection,false,true,false);  
       	XYPlot xyplot = jfreechart.getXYPlot();
        //纵坐标设定
        ValueAxis valueaxis = xyplot.getDomainAxis();
        //自动设置数据轴数据范围
        //valueaxis.setAutoRange(true);
        //数据轴固定数据范围 30s
        valueaxis.setFixedAutoRange(300000D);  
        valueaxis = xyplot.getRangeAxis();
        if(chartContent.equals("1")){
        	valueaxis.setRange(20.0D,40D);
		}else if (chartContent.equals("2")) {
			valueaxis.setRange(0.0D,100D);
		}else if (chartContent.equals("3")) {
			valueaxis.setRange(0.0D,100D);
		}else if (chartContent.equals("4")) {
			valueaxis.setRange(0.0D,3000D);
		}
        
        return jfreechart;
      }  
  
    public void run()  
    {  
    	 
    	while (true) {
    		
    		try {
    			System.out.println("thread"+chartContent);
    			if(chartContent.equals("1")){
    				timeSeries.add(new Millisecond(), Float.parseFloat(NodeJPanel.node_SET.get(id-1).temp_jTextField.getText()));
    				jlabel.setText(NodeJPanel.node_SET.get(id-1).temp_jTextField.getText());
    			}else if (chartContent.equals("2")) {
    				timeSeries.add(new Millisecond(), Float.parseFloat(NodeJPanel.node_SET.get(id-1).humi_jTextField.getText()));
        			jlabel.setText(NodeJPanel.node_SET.get(id-1).humi_jTextField.getText());	
				}else if (chartContent.equals("3")) {
					timeSeries.add(new Millisecond(), Float.parseFloat(NodeJPanel.node_SET.get(id-1).light_jTextField.getText()));
	    			jlabel.setText(NodeJPanel.node_SET.get(id-1).light_jTextField.getText());	
				}else if (chartContent.equals("4")) {
					timeSeries.add(new Millisecond(), Float.parseFloat(NodeJPanel.node_SET.get(id-1).co2_jTextField.getText()));
	    			jlabel.setText(NodeJPanel.node_SET.get(id-1).co2_jTextField.getText());
				}
    			//timeSeries.add(new Millisecond(), randomNum());
    			Thread.sleep(300);  
			} catch (Exception e) {
				// TODO: handle exception
			}
    		
//		   SerialConnect.isReceive = false;  
		}
    }  
      
    private float randomNum()  
    {     
       // System.out.println((Math.random()*20+80));        
        return (float)(Math.random()*5+20);  
    }  
}  
