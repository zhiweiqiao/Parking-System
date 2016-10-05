package com.UI;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author 乔至威 本类为查询收入总额类
 */

public class QueryMoney extends JInternalFrame implements ActionListener {
	private JButton btnSearch = new JButton("查询");
	private JLabel tit = new JLabel("请输入要查询的时间段，时间格式为 yyyy-MM-dd HH:mm:ss");
	private JLabel tit1 = new JLabel("例如 2012-01-01 01:01:01");
	private JLabel tit2 = new JLabel("开始时间：");
	private JLabel tit3 = new JLabel("结束时间：");
	private TextField time1 = new TextField("2012-01-01 01:01:01", 100);
	private TextField time2 = new TextField("2012-01-01 01:01:01", 100);
	private JLabel tit4 = new JLabel("收入总额:");
	private JLabel tit5 = null;
	String z_time1 = null;
	String z_time2 = null;
	String mymoney = null;

	public QueryMoney() {
		setTitle("收入统计");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);
		
		String URL = "jdbc:sqlserver://localhost:1433;databaseName=Parking";
		String name = "sa";
		String password = "123456";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			try {
				conn = DriverManager.getConnection(URL, name, password);
				String search = "select SUM(金额) from money";
				pstmt = conn.prepareStatement(search);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					mymoney = rs.getString(1);
				}
			} catch (SQLException e1) {
				System.out.println("错误1");
			}
		} catch (ClassNotFoundException e1) {
			System.out.println("错误2");
		}
		tit.setBounds(10, 10, 1000, 30);
		tit1.setBounds(10, 40, 1000, 30);
		tit2.setBounds(10, 70, 1000, 30);
		time1.setBounds(10, 100, 300, 25);
		tit3.setBounds(10, 170, 1000, 30);
		tit4.setBounds(500, 150, 1000, 30);	
		time2.setBounds(10, 200, 300, 25);
		btnSearch.setBounds(50, 300, 100, 30);
		btnSearch.addActionListener(this);
		this.add(tit);
		this.add(tit1);
		this.add(tit2);
		this.add(tit3);
		this.add(tit4);
		this.add(time1);
		this.add(time2);
		this.add(btnSearch);
		tit5 = new JLabel(mymoney.trim() + "元");
		tit5.setBounds(600, 150, 1000, 30);
		this.add(tit5);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "查询") {
			z_time1 = time1.getText();
			z_time2 = time2.getText();
			String URL = "jdbc:sqlserver://localhost:1433;databaseName=Parking";
			String name = "sa";
			String password = "123456";

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				try {
					conn = DriverManager.getConnection(URL, name, password);
					String search = "select SUM(金额) from money where 时间 > '" + time1 + "and 时间 < '" + time2 + "'";
					pstmt = conn.prepareStatement(search);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						mymoney = rs.getString(1);
					}
				} catch (SQLException e1) {
					System.out.println("错误1");
				}
			} catch (ClassNotFoundException e1) {
				System.out.println("错误2");
			}
			tit5 = new JLabel(mymoney.trim() + "元");
			tit5.setBounds(600, 150, 1000, 30);
			this.add(tit5);
		}
	}
}
