package com.plant.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;

import com.plant.util.LineDotChart;

import net.sourceforge.chart2d.Chart2D;
/*
 * 数据分析界面
 */
public class ChartHistoryDataPanel extends JPanel {
	public JButton bt1 = new JButton("SEARCH");
	public JSpinner spFrom = new JSpinner();
	public JSpinner spTo = new JSpinner();
	public Chart2D tuBiao;
	public Chart2D tuBiaoNull ;		
	public JComboBox<String> getDataFre;
	public JComboBox<String> getDataKind;
	public JComboBox<String> getDataNode;
	public String[] data = {"10","10"};
	public String[] data2 = {"20","20"};
	public String[][] data3 = {{"null"},{"0"}};
	public ChartHistoryDataPanel() {
		setLayout(null);
		setBounds(0, 0, 600, 600);
//		setForeground(Color.green);
//		setBackground(Color.green);
		setVisible(true);
//		 String[] months = new DateFormatSymbols().getMonths();  //month
//		 SpinnerModel model = new SpinnerListModel(months);
//		 sp = new JSpinner(model);
//		 sp.setBounds(100, 10, 80, 40);
//		 this.add(sp);
		
		SpinnerDateModel model2 = new SpinnerDateModel();
		model2.setCalendarField(Calendar.WEEK_OF_MONTH);
		spFrom = new JSpinner(model2);
		JSpinner.DateEditor editor2 = new JSpinner.DateEditor(
				spFrom,"MM-dd HH:mm:00");
		spFrom.setEditor(editor2);
		spFrom.setBounds(10,15,200,40);
		this.add(spFrom);
		
		JLabel jl = new JLabel("~");
		jl.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		jl.setBounds(220, 15, 80, 40);
		this.add(jl);
		
		SpinnerDateModel model3 = new SpinnerDateModel();
		model3.setCalendarField(Calendar.WEEK_OF_MONTH);
		spTo = new JSpinner(model3);
		JSpinner.DateEditor editor3 = new JSpinner.DateEditor(
				spTo, "MM-dd HH:mm:00");
		spTo.setEditor(editor3);
		spTo.setBounds(250, 15, 200, 40);
		this.add(spTo);
		
		JLabel jl2 = new JLabel("数据频率:");
		jl2.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl2.setBounds(470, 15, 80, 40);
		this.add(jl2);
		
		String[] getDataString ={"4","12","24","48","96","288","1440"};
		String[] getDataString2 ={"1分钟","5分钟","15分钟","30分钟","一小时","两小时","三小时"};
								//3h, 2h , 1h , 30m, 15m, 5m  , 1m
		getDataFre = new JComboBox<String>(getDataString2);
		getDataFre.setBounds(530, 15, 80, 40);
		this.add(getDataFre);
		
		JLabel jl3 = new JLabel("数据类型:");
		jl3.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl3.setBounds(630, 15, 80, 40);
		this.add(jl3);
								
		String[] getDataKindString ={"温度","湿度","光照强度","二氧化碳浓度"};	//2,3,4,5
		getDataKind = new JComboBox<String>(getDataKindString);
		getDataKind.setBounds(690, 15, 110,40);
		
		this.add(getDataKind);
		
		JLabel jl4 = new JLabel("节点:");
		jl4.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		jl4.setBounds(820, 15, 80, 40);
		this.add(jl4);
		
		String[] getDataNodeString ={"1","2","3","4","5","6"};
		getDataNode = new JComboBox<String>(getDataNodeString);
		getDataNode.setBounds(850, 15,60,40);
		this.add(getDataNode);
		
		//gengxing(tuBiao,data);
		tuBiao = LineDotChart.getLineDotChart(data3,0,0);
		tuBiao.setBounds(10, 60, 1325, 600);
		this.add(tuBiao);
		bt1.setBounds(950, 15, 80, 40);
		this.add(bt1);
		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				sdf.format(spFrom.getValue());      //表格查询起始时间string
//				sdf.format(spTo.getValue());        //表格查询结束时间string
//				NodeJPanel.node_SET.get(0).ePoi.readExcelData(1,1,1);//excel查询时间string
				//String[][] data = NodeJPanel.node_SET.get(0).ePoi.readData(spFrom, spTo,3,getDataKind.getSelectedIndex()+2,getDataNode.getSelectedIndex()+1);
				String[][] data = NodeJPanel.node_SET.get(0).ePoi.readDataVersion2(spFrom, spTo,getDataFre.getSelectedIndex(),getDataKind.getSelectedIndex()+2,getDataNode.getSelectedIndex()+1);
//				System.out.println(data[1]);
				gengxing(data,getDataNode.getSelectedIndex()+1,getDataKind.getSelectedIndex()+1);
				
			}
		});
		
	}
	public void gengxing(String [][] data,int node,int kind){
		this.remove(tuBiao);
		tuBiao = LineDotChart.getLineDotChart(data,node,kind);
		tuBiao.setBounds(10, 60, 1325, 600);
		this.add(tuBiao);
		tuBiao.repaint();
		MainFrame.jPanel2.repaint();
	}
}
