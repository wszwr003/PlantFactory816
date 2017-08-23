package com.plant.util;

import java.util.Random;

import javax.swing.JPanel;

import net.sourceforge.chart2d.Chart2D;
import net.sourceforge.chart2d.Chart2DProperties;
import net.sourceforge.chart2d.Dataset;
import net.sourceforge.chart2d.GraphChart2DProperties;
import net.sourceforge.chart2d.GraphProperties;
import net.sourceforge.chart2d.LBChart2D;
import net.sourceforge.chart2d.LegendProperties;
import net.sourceforge.chart2d.MultiColorsProperties;
import net.sourceforge.chart2d.Object2DProperties;

public class LineDotChart{
	public LineDotChart() {
		
	}
	
	
	public static Chart2D getLineDotChart(String[][] data,int node,int kind) {
		    //<-- Begin Chart2D configuration -->

			System.out.println("------------");
		    //Configure object properties
		    Object2DProperties object2DProps = new Object2DProperties();
		    object2DProps.setObjectTitleText ("Data Chart");

		    //Configure chart properties
		    Chart2DProperties chart2DProps = new Chart2DProperties();
		    chart2DProps.setChartDataLabelsPrecision (-2);

		    //Configure legend properties
		    LegendProperties legendProps = new LegendProperties();
		    //String[] legendLabels = {"Node_1", "Node_2", "Node_3", "Node_4", "Node_5", "Node_6"};
		    String[] legendLabels = {"Node"+node};
		    legendProps.setLegendLabelsTexts (legendLabels);

		    //Configure graph chart properties
		    GraphChart2DProperties graphChart2DProps = new GraphChart2DProperties();
		    graphChart2DProps.setLabelsAxisLabelsTexts (data[0]);
		    graphChart2DProps.setLabelsAxisTitleText ("Ê±¼ä");
		    if (kind ==0) {
		    	graphChart2DProps.setNumbersAxisTitleText (" ");
			}else if(kind ==1){
		    	graphChart2DProps.setNumbersAxisTitleText ("¡æ");
			}else if(kind ==2){
		    	graphChart2DProps.setNumbersAxisTitleText ("%");
			}else if(kind ==3){
		    	graphChart2DProps.setNumbersAxisTitleText ("£õmol/m2/s");
			}else if(kind ==4){
		    	graphChart2DProps.setNumbersAxisTitleText ("ppm");
			}
		    graphChart2DProps.setLabelsAxisTicksAlignment (graphChart2DProps.CENTERED);
		    graphChart2DProps.setChartDatasetCustomizeGreatestValue (true);
		    graphChart2DProps.setChartDatasetCustomGreatestValue (30.0f);
		    graphChart2DProps.setChartDatasetCustomizeLeastValue (true);
		    graphChart2DProps.setChartDatasetCustomLeastValue (25.0f);
		    //Configure graph properties
		    GraphProperties graphProps = new GraphProperties();
		    graphProps.setGraphBarsExistence (false);
		    graphProps.setGraphLinesExistence (true);
		    graphProps.setGraphLinesThicknessModel (2);
		    graphProps.setGraphLinesWithinCategoryOverlapRatio (1f);
		    graphProps.setGraphDotsExistence (true);
		    graphProps.setGraphDotsThicknessModel (10);
		    graphProps.setGraphDotsWithinCategoryOverlapRatio (1f);
		    graphProps.setGraphAllowComponentAlignment (true);
		    

		    //Configure dataset
		    Random random = new Random();
		    Dataset dataset = new Dataset (1,data[1].length,1);
		    for (int i = 0; i < dataset.getNumSets(); ++i) {
		      for (int j = 0; j < dataset.getNumCats(); ++j) {
		        for (int k = 0; k < dataset.getNumItems(); ++k) {
		          int increaseMetric = dataset.getNumSets() - i - 1;
		          //dataset.set(i, j, k,Float.valueOf(data[j]));
		          System.out.println(data[1][j]);
		          dataset.set(i, j, k,Float.valueOf(data[1][j]));
		          //dataset.set (i, j, k,(float)100.5);
		        }
		      }
		    }

		    //Configure graph component colors
		    MultiColorsProperties multiColorsProps = new MultiColorsProperties();

		    //Configure chart
		    LBChart2D chart2D = new LBChart2D();
		    chart2D.setObject2DProperties (object2DProps);
		    chart2D.setChart2DProperties (chart2DProps);
		    chart2D.setLegendProperties (legendProps);
		    chart2D.setGraphChart2DProperties (graphChart2DProps);
		    chart2D.addGraphProperties (graphProps);
		    chart2D.addDataset (dataset);
		    chart2D.addMultiColorsProperties (multiColorsProps);

		    //Optional validation:  Prints debug messages if invalid only.
		    if (!chart2D.validate (false)) chart2D.validate (true);

		    //<-- End Chart2D configuration -->
		    System.out.println("------------");
		    return chart2D;
		  }
}
