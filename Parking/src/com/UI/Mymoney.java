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
 * @author ������ 
 * ����Ϊ�û���ѯͣ���������
 */

public class Mymoney extends JInternalFrame {
	private JLabel tit1 = new JLabel("�˻����:");
	private JLabel tit2;
	private JLabel tit3 = new JLabel("������㣬�뼰ʱ��ֵ������Ӱ������ʹ�ã�");
	String mymoney;

	public Mymoney(String num) {
		setTitle("�˻����");
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
				String search = "select ��� from card where ����=?";
				pstmt = conn.prepareStatement(search);
				pstmt.setString(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					mymoney = rs.getString(1);
				}
				double z_money = Double.parseDouble(mymoney.trim());
				if (z_money < 10) {
					JOptionPane.showMessageDialog(this,
							"�����˻����С��10Ԫ���뼰ʱ��ֵ������Ӱ������ʹ�ã�");
				}

			} catch (SQLException e1) {
				System.out.println("����1");
			}
		} catch (ClassNotFoundException e1) {
			System.out.println("����2");
		}

		tit1.setBounds(20, 80, 100, 30);
		this.add(tit1);
		tit2 = new JLabel(mymoney.trim() + "Ԫ");
		tit2.setBounds(100, 80, 100, 30);
		this.add(tit2);
		tit3.setBounds(20, 160, 1000, 30);
		this.add(tit3);
		this.setVisible(true);
	}
}