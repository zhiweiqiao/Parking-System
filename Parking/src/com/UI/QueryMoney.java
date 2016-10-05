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
 * @author ������ ����Ϊ��ѯ�����ܶ���
 */

public class QueryMoney extends JInternalFrame implements ActionListener {
	private JButton btnSearch = new JButton("��ѯ");
	private JLabel tit = new JLabel("������Ҫ��ѯ��ʱ��Σ�ʱ���ʽΪ yyyy-MM-dd HH:mm:ss");
	private JLabel tit1 = new JLabel("���� 2012-01-01 01:01:01");
	private JLabel tit2 = new JLabel("��ʼʱ�䣺");
	private JLabel tit3 = new JLabel("����ʱ�䣺");
	private TextField time1 = new TextField("2012-01-01 01:01:01", 100);
	private TextField time2 = new TextField("2012-01-01 01:01:01", 100);
	private JLabel tit4 = new JLabel("�����ܶ�:");
	private JLabel tit5 = null;
	String z_time1 = null;
	String z_time2 = null;
	String mymoney = null;

	public QueryMoney() {
		setTitle("����ͳ��");
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
				String search = "select SUM(���) from money";
				pstmt = conn.prepareStatement(search);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					mymoney = rs.getString(1);
				}
			} catch (SQLException e1) {
				System.out.println("����1");
			}
		} catch (ClassNotFoundException e1) {
			System.out.println("����2");
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
		tit5 = new JLabel(mymoney.trim() + "Ԫ");
		tit5.setBounds(600, 150, 1000, 30);
		this.add(tit5);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "��ѯ") {
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
					String search = "select SUM(���) from money where ʱ�� > '" + time1 + "and ʱ�� < '" + time2 + "'";
					pstmt = conn.prepareStatement(search);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						mymoney = rs.getString(1);
					}
				} catch (SQLException e1) {
					System.out.println("����1");
				}
			} catch (ClassNotFoundException e1) {
				System.out.println("����2");
			}
			tit5 = new JLabel(mymoney.trim() + "Ԫ");
			tit5.setBounds(600, 150, 1000, 30);
			this.add(tit5);
		}
	}
}
