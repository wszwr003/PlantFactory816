package com.plant.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JSpinner;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jfree.ui.ApplicationFrame;

public class ExcelPOI {
      
      private POIFSFileSystem fs;  
	  private  HSSFWorkbook wb;
	  HSSFSheet sheet;
	  HSSFFont font;
	  HSSFCellStyle style;
	  HSSFRow row;
	  HSSFCell cell;

	public ExcelPOI(int id){
		try {
			File file = new File("src//excel//"+"Node_"+id+".xls");
			FileInputStream fis = new FileInputStream(file);
			wb = new HSSFWorkbook(fis);
			sheet = this.wb.getSheet("sheet1");
		} catch (FileNotFoundException e) {
			wb = new HSSFWorkbook();
			sheet = this.wb.createSheet("sheet1");
			font = this.wb.createFont();
			style = this.wb.createCellStyle();
			row = this.sheet.createRow(0);
			cell = this.row.createCell(0);
			 // 创建Excel的工作sheet,对应到一个excel文档的tab
	        // 设置excel每列宽度
	        sheet.setColumnWidth(0, 2000);
	        sheet.setColumnWidth(1, 5000);
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
	        sheet.setColumnWidth(9, 5000);
	        sheet.setColumnWidth(10, 5000);
	        // 创建字体样式
	        font.setFontName("Verdana");
	        font.setBoldweight((short) 100);
	        font.setFontHeight((short) 300);
	        font.setColor(HSSFColor.BLUE.index);

	        // 创建单元格样式
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
	        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

	        // 设置边框
	        style.setBottomBorderColor(HSSFColor.RED.index);
	        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

	        style.setFont(font);// 设置字体

	        // 创建Excel的sheet的一行
	        row.setHeight((short) 500);// 设定行的高度
	        // 创建一个Excel的单元格

	        // 合并单元格(startRow，endRow，startColumn，endColumn)
//	        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
	        // 给Excel的单元格设置样式和赋值
	        cell.setCellStyle(style);
	        cell.setCellValue("ID");
	        cell = row.createCell(1);
	        cell.setCellStyle(style);
	        cell.setCellValue("日期");
	        cell = row.createCell(2);
	        cell.setCellStyle(style);
	        cell.setCellValue("温度");
	        cell = row.createCell(3);
	        cell.setCellStyle(style);
	        cell.setCellValue("湿度");
	        cell = row.createCell(4);
	        cell.setCellStyle(style);
	        cell.setCellValue("光照");
	        cell = row.createCell(5);
	        cell.setCellStyle(style);
	        cell.setCellValue("二氧化碳");
	        cell = row.createCell(6);
	        cell.setCellStyle(style);
	        cell.setCellValue("--");
	        cell = row.createCell(7);
	        cell.setCellStyle(style);
	        cell.setCellValue("--");
	        cell = row.createCell(8);
	        cell.setCellStyle(style);
	        cell.setCellValue("--");
	        cell = row.createCell(9);
	        cell.setCellStyle(style);
	        cell.setCellValue("--");
	        cell = row.createCell(10);
	        cell.setCellStyle(style);
	        cell.setCellValue("--");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        

	}
      
		/**
         * @param args
         */
	 public void sqlToExcel(String name, String[][] temp)
			    throws IOException
			  {
			    for (int i = 0; i < temp.length; i++)
			    {
			      this.row = this.sheet.createRow(i + 1);
			      for (int j = 0; j < temp[i].length; j++)
			      {
			        this.cell = this.row.createCell(j);
			        this.cell.setCellValue(temp[i][j]);
			      }
			    }
			    FileOutputStream os = new FileOutputStream("d:\\" + name + ".xls");
			    this.wb.write(os);
			    os.close();
			  }
			  
	  public void writeLocalExcel(String name, String[] temp)
			    throws IOException
			  {
			    this.row = this.sheet.createRow(this.sheet.getLastRowNum() + 1);
			    temp[0] = Integer.toString(this.sheet.getLastRowNum());
			    for (int j = 0; j < temp.length; j++)
			    {
			      this.cell = this.row.createCell(j);
			      if (j != 1) {
			    	this.cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
			        this.cell.setCellValue(Double.parseDouble(temp[j]));
			      } else {
			        this.cell.setCellValue(temp[j]);
			      }
			    }
			    //FileOutputStream os = new FileOutputStream("d:\\" + name + ".xls");
			    
			    FileOutputStream os = new FileOutputStream("src\\excel\\" + name + ".xls");
			    this.wb.write(os);
			    os.close();
	        	CopyUtil.copyDoc("src\\excel\\"+ name + ".xls", "excel\\"+name + ".xls");

			  }  
	  public String[][] readData(JSpinner start,JSpinner end,int Fre,int sensor,int node){
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm:00");
		String[][] data;
		int j=0,k=0;
		try {  
        	//InputStream is = new FileInputStream("d:\\" + "Node_" + node + ".xls");  
        	InputStream is = new FileInputStream("src\\excel\\"+ "Node_"+ node + ".xls");  
        	fs = new POIFSFileSystem(is);  
            wb = new HSSFWorkbook(fs);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        sheet = wb.getSheetAt(0);
		//System.out.println( sheet.getLastRowNum());
        //System.out.println(sdf.format(start.getValue()));
        //System.out.println(readExcelData(node,2,1));
		//sdf.format(start.getValue());      //表格查询起始时间string
		//sdf.format(end.getValue());        //表格查询结束时间string
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			if (readExcelData(node,i,1).equals(sdf.format(start.getValue()))) {
				j=i;
				//System.out.println(j);
			}
			if (readExcelData(node,i,1).equals(sdf.format(end.getValue()))) {
				k=i;
				//System.out.println(k);
				break;
			}
		}
		data = new String[2][k-j+1];
		for (int i = 0; i < k-j+1; i++) {
			data[0][i]=readExcelData(node,j+i,1);  //时间
			data[1][i]=readExcelData(node,j+i,sensor);  //数据
			System.out.println(data[1][i]);  //return ID
			//System.out.println(readExcelData(node,j+i,0));  //return ID
		}
		//System.out.println(data.length);
		return data;
	  }
	  public String[][] readDataVersion2(JSpinner start,JSpinner end,int Fre,int sensor,int node){
			SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");
			String[][] data;
			int j=0,k=0,flag =0, flag2=0;
			int[] getDataFre ={1,5,15,30,60,120,180};
			try {  
	        	//InputStream is = new FileInputStream("d:\\" + "Node_" + node + ".xls");  
	        	InputStream is = new FileInputStream("src\\excel\\"+ "Node_"+ node + ".xls");  
	        	fs = new POIFSFileSystem(is);  
	            wb = new HSSFWorkbook(fs);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				if (readExcelData(node,i,1).compareTo(sdf.format(start.getValue()))>=0) {
					flag =1;
					j=i;
					System.out.println(j);  //j为起始行
					break;
				}
			}
			for (int i = sheet.getLastRowNum(); i >= 1; i--) {
				if (readExcelData(node,i,1).compareTo(sdf.format(end.getValue()))<=0) {
					flag2=1;
					k=i;
					System.out.println(k);  //k为结束行
					break;
				}
				
			}
			if(!(flag==1&&flag2==1)) {
				//如果搜索超出范围
				data = new String[2][1];
				data[0][0]="null";
				data[1][0]="0";
				return data;
			}
			data = new String[2][(k-j+1)/(getDataFre[Fre])];
			for (int i = 0; i <(k-j+1)/(getDataFre[Fre]); i++) {
				data[0][i]=readExcelData(node,j+i*getDataFre[Fre],1);  //时间
				data[1][i]=readExcelData(node,j+i*getDataFre[Fre],sensor);  //数据
				System.out.println(data[1][i]);  //return ID
			}
			return data;
		  }
	  public String[][] readData2(JSpinner start,JSpinner end,int Fre,int sensor,int node){
			SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm:00");
			String[][] data;
			int j=0,k=0;
			try {  
	        	//InputStream is = new FileInputStream("d:\\" + "Node_" + node + ".xls");  
	        	InputStream is = new FileInputStream("src\\excel\\"+ "Node_"+ node + ".xls");  
	        	fs = new POIFSFileSystem(is);  
	            wb = new HSSFWorkbook(fs);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        sheet = wb.getSheetAt(0);
			//System.out.println( sheet.getLastRowNum());
	        //System.out.println(sdf.format(start.getValue()));
	        //System.out.println(readExcelData(node,2,1));
			//sdf.format(start.getValue());      //表格查询起始时间string
			//sdf.format(end.getValue());        //表格查询结束时间string
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				if (readExcelData(node,i,1).equals(sdf.format(start.getValue()))) {
					j=i;
					//System.out.println(j);
				}
				if (readExcelData(node,i,1).equals(sdf.format(end.getValue()))) {
					k=i;
					//System.out.println(k);
					break;
				}
			}
			data = new String[2][k-j+1];
			for (int i = 0; i < k-j+1; i++) {
				data[0][i]=readExcelData(node,j+i,1);  //时间
				data[1][i]=readExcelData(node,j+i,sensor);  //数据
				System.out.println(data[1][i]);  //return ID
				//System.out.println(readExcelData(node,j+i,0));  //return ID
			}
			//System.out.println(data.length);
			return data;
		  }
	  
	  public String readExcelData(int node,int hang,int lie) {  
	        try {  
	        	InputStream is = new FileInputStream("excel\\" + "Node_" + node + ".xls");  
	        	fs = new POIFSFileSystem(is);  
	            wb = new HSSFWorkbook(fs);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        sheet = wb.getSheetAt(0);  
	        row = sheet.getRow(hang);  
	        // 标题总列数  
//	        int colNum = row.getPhysicalNumberOfCells();  
	        int colNum = 6;  
	        
	        //System.out.println("colNum:" + colNum);  
	        String[] title = new String[colNum];  
	        for (int i = 0; i < colNum; i++) {  
	            title[i] = getStringCellValue(row.getCell((int) i));  
	            //title[i] = getCellFormatValue(row.getCell((short) i));  
	            //System.out.println(title[i]);
	        }  
	        //System.out.println(title[lie]);
	        return title[lie];  
	    }  
	  
	  private String getStringCellValue(HSSFCell cell) {  
	        String strCell = "";  
	        switch (cell.getCellType()) {  
	        case HSSFCell.CELL_TYPE_STRING:  
	            strCell = cell.getStringCellValue();  
	            break;  
	        case HSSFCell.CELL_TYPE_NUMERIC:  
	            strCell = String.valueOf(cell.getNumericCellValue());  
	            break;  
	        case HSSFCell.CELL_TYPE_BOOLEAN:  
	            strCell = String.valueOf(cell.getBooleanCellValue());  
	            break;  
	        case HSSFCell.CELL_TYPE_BLANK:  
	            strCell = "";  
	            break;  
	        default:  
	            strCell = "";  
	            break;  
	        }  
	        if (strCell.equals("") || strCell == null) {  
	            return "";  
	        }  
	        if (cell == null) {  
	            return "";  
	        }  
	        return strCell;  
	    }
	

}