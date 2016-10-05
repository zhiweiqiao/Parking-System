package com.DAL;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author 乔至威
 * 本类为数据访问层的数据库操作类，集成了一些数据库的操作方法
 * 这些方法负责访问数据库操作并返回相应的信息
 * 在本层的其它类中只需调用该类中的方法直接获得结果即可
 * 在访问数据库之前，应该先调用 getStatement() 方法初始化执行语句对象
 * 执行完数据库操作、处理相应数据后，应调用 closeAll()方法关闭Statement和Connection
 * 注意如果使用了 ResultSet，需自己手动关闭
 */

public class SqlExecute 
{
	private Connection conn;  	//数据库连接对象
	private Statement stmt;   	//语句执行对象
	private ResultSet rset;		//结果集
	
	private void getStatement() throws Exception 
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;DatabaseName=Parking;user=sa;password=123456;");
			stmt = conn.createStatement();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	//关闭使用完的数据库连接，当完成数据库操作后应该调用此方法关闭连接
	public void closeAll() throws SQLException 
	{
		try {
			if( stmt != null) 
			{
				stmt.close();
				stmt = null;
			}
			if( !conn.isClosed()) 
				conn.close();
		}
		catch(SQLException se) 
		{
			throw se;
		}
	}
	
	/**
	 * 执行sql 语句，凡是非查询类的数据库操作，应该调用该方法
	 */
	public int executeSql(String sql) throws Exception
	{
		int recoders = 0;
		
		getStatement();

		recoders = stmt.executeUpdate(sql);

		closeAll();
		
		return recoders;
	}
	
	/**
	 * 执行数据库查询语句，获得一个 ResultSet，并用此填充 JTable，然后返回JTable对象
	 */
	public JTable getTable(String sql) throws Exception
	{
		getStatement();

		rset = stmt.executeQuery(sql);
		
		ResultSetMetaData rsmd=rset.getMetaData();         //返回元数据对象
        
		//获得列数，注意这个值后面多处用到
		int columns = rsmd.getColumnCount();   
		
        String columntitle[] = new String[columns];        //创建列名数组
        for (int j=1; j<=columns; j++)
            columntitle[j-1] = rsmd.getColumnLabel(j);     //获得列名填充表格标题数组
        
        DefaultTableModel tm = new DefaultTableModel(columntitle, 0); 

        String results[]= new String[columns]; 
        
        while (rset.next()) 
        {                               
            for(int i=0; i<columns; i++)
            	results[i] = rset.getString(i+1);	
            tm.addRow(results);
        }
        
        JTable tb = new JTable();
        tb.setModel(tm);

        rset.close();

		closeAll();
		
		return tb;
	}
	
	public ResultSet getResultSet(String sql) throws Exception
	{
		getStatement();

		ResultSet rs =  stmt.executeQuery(sql);

		return rs;
	}
}
