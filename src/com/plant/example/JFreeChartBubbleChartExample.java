package com.plant.example;


import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.MatrixSeriesCollection;
import org.jfree.data.xy.NormalizedMatrixSeries;

public class JFreeChartBubbleChartExample extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int SIZE = 10;
    public JFreeChartBubbleChartExample(String applicationTitle) {
        super(applicationTitle);
        // Creates a sample dataset for bubble chart
        final NormalizedMatrixSeries series = new NormalizedMatrixSeries("Grid 1", SIZE, SIZE);
        for (int count = 0; count < SIZE; count++) {
            final int i = (int) (Math.random() * SIZE);
            final int j = (int) (Math.random() * SIZE);
            final int k = (int) (Math.random() * SIZE);
            series.update(i, j, k);
        }
        series.setScaleFactor(series.getItemCount());
        MatrixSeriesCollection dataSet = new MatrixSeriesCollection(series);

        // Based on the dataset we are creating BubbleChart
        JFreeChart bubbleChart = ChartFactory.createBubbleChart("Population Details", "X", "Y", dataSet, PlotOrientation.VERTICAL, true, true,false);
        XYPlot plot = bubbleChart.getXYPlot();
        plot.setForegroundAlpha(0.6f);
        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setLowerBound(-0.4);
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLowerBound(-0.4);
        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(bubbleChart);
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add to contentPane
        setContentPane(chartPanel);
    }

    public static void main(String[] args) {
        JFreeChartBubbleChartExample chart = new JFreeChartBubbleChartExample("Population Usage Statistics");
        chart.pack();
        chart.setVisible(true);
    }
}
