package com.DAL;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author ������
 * ����Ϊ���ݷ��ʲ�����ݿ�����࣬������һЩ���ݿ�Ĳ�������
 * ��Щ��������������ݿ������������Ӧ����Ϣ
 * �ڱ������������ֻ����ø����еķ���ֱ�ӻ�ý������
 * �ڷ������ݿ�֮ǰ��Ӧ���ȵ��� getStatement() ������ʼ��ִ��������
 * ִ�������ݿ������������Ӧ���ݺ�Ӧ���� closeAll()�����ر�Statement��Connection
 * ע�����ʹ���� ResultSet�����Լ��ֶ��ر�
 */

public class SqlExecute 
{
	private Connection conn;  	//���ݿ����Ӷ���
	private Statement stmt;   	//���ִ�ж���
	private ResultSet rset;		//�����
	
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
	
	//�ر�ʹ��������ݿ����ӣ���������ݿ������Ӧ�õ��ô˷����ر�����
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
	 * ִ��sql ��䣬���Ƿǲ�ѯ������ݿ������Ӧ�õ��ø÷���
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
	 * ִ�����ݿ��ѯ��䣬���һ�� ResultSet�����ô���� JTable��Ȼ�󷵻�JTable����
	 */
	public JTable getTable(String sql) throws Exception
	{
		getStatement();

		rset = stmt.executeQuery(sql);
		
		ResultSetMetaData rsmd=rset.getMetaData();         //����Ԫ���ݶ���
        
		//���������ע�����ֵ����ദ�õ�
		int columns = rsmd.getColumnCount();   
		
        String columntitle[] = new String[columns];        //������������
        for (int j=1; j<=columns; j++)
            columntitle[j-1] = rsmd.getColumnLabel(j);     //�������������������
        
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
