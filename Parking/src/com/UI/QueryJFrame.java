package com.UI;

import javax.swing.*;
import java.sql.*;

/**
 * @author ������ 
 * ����Ϊ��ѯ������
 */

public class QueryJFrame extends JFrame {
	private Connection conn; // ���ݿ����Ӷ���

	public QueryJFrame(String driver, String url, String table)
			throws ClassNotFoundException, SQLException // ���췽����tableָ�����ݿ��еı���
	{
		super(table);
		this.setBounds(300, 240, 700, 320);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Class.forName(driver); // ָ��JDBC��������
		this.conn = DriverManager.getConnection(url); // �������ݿ����Ӷ���
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
		ResultSetMetaData rsmd = rset.getMetaData(); // ����Ԫ���ݶ���
		int columns = rsmd.getColumnCount(); // �������
		String columntitle[] = new String[columns]; // ������������
		for (int j = 1; j <= columns; j++)
			columntitle[j - 1] = rsmd.getColumnLabel(j); // �������

		int rows = 0;
		while (rset.next())
			// �����������������ý����������
			rows++;
		System.out.println("rset.isAfterLast()=" + rset.isAfterLast());

		String results[][] = new String[rows][columns]; // ������ά���鱣�����ݽ����

		for (int i = rows - 1; rset.previous(); i--)
			// �Ӻ���ǰ����ÿ�У����ÿ������
			for (int j = 1; j <= columns; j++)
				results[i][j - 1] = rset.getString(j); // ��õ�ǰ��ָ���е�ֵ
		rset.close();
		stmt.close();
		return new JTable(results, columntitle);
	}

	public void finalize() throws SQLException // �����������ر����ݿ�����
	{
		this.conn.close();
	}

	public static void main(String args[]) throws Exception {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ָ��MySQL
																		// JDBC��������
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Parking;user=sa;password=123456;";
		new QueryJFrame(driver, url, "park");
	}
}