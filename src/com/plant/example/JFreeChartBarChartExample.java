package com.plant.example;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class JFreeChartBarChartExample extends JFrame {

   private static final long serialVersionUID = 1L;

   public JFreeChartBarChartExample(String applicationTitle, String chartTitle) {
        super(applicationTitle);

        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createBarChart(chartTitle, "Category", "Score", createDataset(),PlotOrientation.VERTICAL, true, true, false);

        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
      
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
      
        // add to contentPane
        setContentPane(chartPanel);
    }
  
   private CategoryDataset createDataset() {
     
      // row keys...
      final String firefox = "Firefox";
      final String chrome = "Chrome";
      final String iexplorer = "InternetExplorer";

      // column keys...
      final String speed = "Speed";
      final String popular = "Popular";
      final String response = "Response";
      final String osindependent = "OS Independent";
      final String features = "Features";

      // create the dataset...
      final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

      dataset.addValue(1.0, firefox, speed);
      dataset.addValue(4.0, firefox, popular);
      dataset.addValue(3.0, firefox, response);
      dataset.addValue(5.0, firefox, osindependent);
      dataset.addValue(5.0, firefox, features);

      dataset.addValue(5.0, chrome, speed);
      dataset.addValue(7.0, chrome, popular);
      dataset.addValue(6.0, chrome, response);
      dataset.addValue(8.0, chrome, osindependent);
      dataset.addValue(4.0, chrome, features);

      dataset.addValue(4.0, iexplorer, speed);
      dataset.addValue(3.0, iexplorer, popular);
      dataset.addValue(2.0, iexplorer, response);
      dataset.addValue(3.0, iexplorer, osindependent);
      dataset.addValue(6.0, iexplorer, features);
     
      return dataset;
     
  }

   public static void main(String[] args) {
      JFreeChartBarChartExample chart = new JFreeChartBarChartExample("Browser Usage Statistics", "Which Browser are you using?");
      chart.pack();
      chart.setVisible(true);
   }
}