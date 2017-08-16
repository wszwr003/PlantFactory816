package com.plant.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 数据库连接类
 * @author MR.WANG
 *
 */
public class Dao {
	protected static String dbClassName = "com.mysql.jdbc.Driver";
	protected static String dbUrl = "jdbc:mysql://45.78.39.6:3306/db_microchip";
	protected static String dbUser = "root";
	protected static String dbPwd = "123456";
	protected static String second = null;
	private   static Connection conn = null;
	
	private Dao() {
		try {
			if (conn == null) {
				Class.forName(dbClassName).newInstance();
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
			}
			else
				return;
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}

	private static ResultSet executeQuery(String sql) {
		try {
			if(conn==null)
			new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
		}
	}
	
	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn = null;
		}
	}

	public static String[][] downLoad(String date1,String date2) throws IOException {
		String sql = "select *  from tb_sensor where date between '"+date1+"' and +'"+date2+"'";
		ResultSet rs = Dao.executeQuery(sql);
		String[][] temp = new String[500][11];
		int i=0;
		try {
			while (rs.next()) {
//				System.out.print(rs.getString("id"));		
				temp[i][0]= rs.getString("id");
				temp[i][1]= rs.getString("date");
				temp[i][2]= rs.getString("diode1");
				temp[i][3]= rs.getString("diode2");
				temp[i][4]= rs.getString("diode3");
				temp[i][5]= rs.getString("diode4");
				temp[i][6]= rs.getString("diode5");
				temp[i][7]= rs.getString("od");
				temp[i][8]= rs.getString("temp");
				temp[i][9]= rs.getString("oxide");
				temp[i][10]= rs.getString("ph");
				System.out.println(temp[i][0]);	
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return temp;
	
	}
	
	public static int insertSensor(double diode1,double diode2,double diode3,double diode4,double diode5,double od,double temp,double oxide,double ph,String date){
		int i=0;
		try{
			String sql="insert into tb_sensor(diode1,diode2,diode3,diode4,diode5,od,temp,oxide,ph,date) values("+diode1+","+diode2+","+diode3+","+diode4+","+diode5+","+od+","+temp+","+oxide+","+ph+",'"+date+"')";

			System.out.println(sql);
			i=Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	
	private static int executeUpdate(String sql) {
		
		try {
			if(conn==null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			//if(e.getMessage().equals("[Microsoft][SQLServer 2000 Driver for JDBC][SQLServer]DELETE 语句与 COLUMN REFERENCE 约束 'FK_TB_BORRO_REFERENCE_TB_BOOKI' 冲突。该冲突发生于数据库 'db_library'，表 'tb_borrow', column 'bookISBN'。"))
				
			return -1;
		} finally {
		}
	}

}
