package com.plant.example;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class JFreeChartPolarChartExample extends JFrame {

    private static final long serialVersionUID = 1L;

    public JFreeChartPolarChartExample(String applicationTitle) {
        super(applicationTitle);
        // Creates a sample dataset for polar chart

        XYSeriesCollection  dataSet = new XYSeriesCollection();
        XYSeries series1 = createRandomData("Series 1", 77.0, 8.0);
        XYSeries series2 = createRandomData("Series 2", 52.0, 6.0);
        XYSeries series3 = createRandomData("Series 3", 32.0, 2.0);
        dataSet.addSeries(series1);
        dataSet.addSeries(series2);
        dataSet.addSeries(series3);
        // Based on the dataset we are creating BubbleChart
        JFreeChart polarChart = ChartFactory.createPolarChart("Polar Chart Example", dataSet, true, true, false);
        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(polarChart);
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add to contentPane
        setContentPane(chartPanel);
    }

    private static XYSeries createRandomData(final String name, final double baseRadius, final double thetaInc) {
        final XYSeries series = new XYSeries(name);
        for (double coeff = 0.0; coeff < 365.0; coeff += thetaInc) {
            final double radius = baseRadius * (1.1 + Math.random());
            series.add(coeff, radius);
        }
        return series;
    }

    public static void main(String[] args) {
        JFreeChartPolarChartExample chart = new JFreeChartPolarChartExample("Polar Chart Example");
        chart.pack();
        chart.setVisible(true);
    }
}
