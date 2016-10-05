package com.UI;

import javax.swing.*;
import java.sql.*;

/**
 * @author 乔至威 
 * 本类为查询界面类
 */

public class QueryJFrame extends JFrame {
	private Connection conn; // 数据库连接对象

	public QueryJFrame(String driver, String url, String table)
			throws ClassNotFoundException, SQLException // 构造方法，table指定数据库中的表名
	{
		super(table);
		this.setBounds(300, 240, 700, 320);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Class.forName(driver); // 指定JDBC驱动程序
		this.conn = DriverManager.getConnection(url); // 返回数据库连接对象
		JTable jtable = query(table);
		this.getContentPane().add(new JScrollPane(jtable));
		this.setVisible(true);
	}

	public JTable query(String table) throws SQLException {
		String sql = "SELECT * FROM " + table;
		Statement stmt = this.conn.createStatement(1003, 1007);
		ResultSet rset = stmt.executeQuery(sql);
		System.out.println("rset.getType()=" + rset.getType());
		System.out.println("rset.getConcurrency()=" + rset.getConcurrency());
		System.out.println("rset.isBeforeFirst()=" + rset.isBeforeFirst());
		ResultSetMetaData rsmd = rset.getMetaData(); // 返回元数据对象
		int columns = rsmd.getColumnCount(); // 获得列数
		String columntitle[] = new String[columns]; // 创建列名数组
		for (int j = 1; j <= columns; j++)
			columntitle[j - 1] = rsmd.getColumnLabel(j); // 获得列名

		int rows = 0;
		while (rset.next())
			// 迭代遍历结果集，获得结果集总行数
			rows++;
		System.out.println("rset.isAfterLast()=" + rset.isAfterLast());

		String results[][] = new String[rows][columns]; // 创建二维数组保存数据结果集

		for (int i = rows - 1; rset.previous(); i--)
			// 从后向前访问每行，获得每行数据
			for (int j = 1; j <= columns; j++)
				results[i][j - 1] = rset.getString(j); // 获得当前行指定列的值
		rset.close();
		stmt.close();
		return new JTable(results, columntitle);
	}

	public void finalize() throws SQLException // 析构方法，关闭数据库连接
	{
		this.conn.close();
	}

	public static void main(String args[]) throws Exception {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 指定MySQL
																		// JDBC驱动程序
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Parking;user=sa;password=123456;";
		new QueryJFrame(driver, url, "park");
	}
}