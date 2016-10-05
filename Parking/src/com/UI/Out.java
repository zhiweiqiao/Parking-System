package com.UI;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import com.DAO.MyTime;

/**
 * @author ������ ����Ϊͣ��������������
 */

public class Out extends JInternalFrame implements ActionListener {
	private JLabel tit1 = new JLabel("���ţ�");
	private JLabel tit2 = new JLabel("���ƺţ�");
	private TextField num = new TextField(200);
	private TextField car = new TextField(200);
	JButton submit = new JButton("�ύ");
	JButton quit = new JButton("�˳�");
	JButton reset = new JButton("����");
	double price = 2.0;
	double price1 = 1.5;

	public Out() {
		setTitle("��������");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		tit1.setBounds(20, 80, 100, 30);
		this.add(tit1);
		num.setBounds(120, 80, 180, 25);
		this.add(num);

		tit2.setBounds(20, 120, 100, 30);
		this.add(tit2);
		car.setBounds(120, 120, 180, 25);
		this.add(car);

		submit.setBounds(120, 300, 100, 30);
		submit.addActionListener(this);
		this.add(submit);
		reset.setBounds(120, 360, 100, 30);
		reset.addActionListener(this);
		this.add(reset);
		quit.setBounds(260, 300, 100, 30);
		quit.addActionListener(this);
		this.add(quit);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String z_num = num.getText().trim();
		String z_car = car.getText().trim();
		MyTime z_time = new MyTime();
		String z_oldtime = null;
		String z_type = null;
		String z_newmoney = null;
		String z_park = null;

		if (e.getActionCommand() == "�ύ") {
			String URL = "jdbc:sqlserver://localhost:1433;databaseName=Parking";
			String userName = "sa";
			String password = "123456";

			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				try {
					conn = DriverManager.getConnection(URL, userName, password);
					String check = "select ���� from card where ����=?";
					pstmt = conn.prepareStatement(check);
					pstmt.setString(1, z_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						conn = DriverManager.getConnection(URL, userName,
								password);
						String check1 = "select ͣ�������� from card where ����=?";
						pstmt = conn.prepareStatement(check1);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							z_type = rs.getString(1).trim();
						}
						conn = DriverManager.getConnection(URL, userName,
								password);

						String search = "select ͣ��ʱ�� from enter where ����=?";
						pstmt = conn.prepareStatement(search);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							z_oldtime = rs.getString(1).trim();
						} else {
							JOptionPane
									.showMessageDialog(this, "�Բ��𣬱�ͣ����û��ͣ����");
						}
						SimpleDateFormat df = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						Date date = df.parse(z_oldtime);
						double h1 = date.getHours();
						double d1 = date.getDate();
						double m1 = date.getMonth() + 1;
						Date date1 = new Date();
						double h2 = date1.getHours();
						double d2 = date1.getDate();
						double m2 = date1.getMonth() + 1;
						double allmoney = 0.0;
						if (z_type.trim().equals("�Ż�")) {
							allmoney = (h2 - h1) * price1 + (d2 - d1) * 24
									* price1 + (m2 - m1) * 30 * 24 * price1;
						} else {
							allmoney = (h2 - h1) * price + (d2 - d1) * 24
									* price + (m2 - m1) * 30 * 24 * price;
						}
						if (allmoney == 0) {
							allmoney += price;
						}
						System.out.println(h1);
						System.out.println(h2);
						System.out.println(d1);
						System.out.println(d2);
						System.out.println(allmoney);

						String search1 = "select ��λ�� from enter where ����=?";
						pstmt = conn.prepareStatement(search1);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							z_park = rs.getString(1);
						}

						String delete = "delete from enter where ����=?";
						pstmt = conn.prepareStatement(delete);
						pstmt.setString(1, z_num);
						pstmt.execute();

						String insert = "insert into history values(?,?,?,?,?)";
						pstmt = conn.prepareStatement(insert);
						pstmt.setString(1, z_num);
						pstmt.setString(2, z_car);
						pstmt.setString(3, z_park);
						pstmt.setString(4, z_time.getTime());
						pstmt.setString(5, "����");
						pstmt.execute();

						String search2 = "select ��� from card where ����=?";
						pstmt = conn.prepareStatement(search2);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							double oldmoney = Double.parseDouble(rs
									.getString(1));
							double newmoney = oldmoney - allmoney;
							z_newmoney = String.valueOf(newmoney);
							String update = "update card set ���=? where ����=?";
							pstmt = conn.prepareStatement(update);
							pstmt.setString(1, z_newmoney);
							pstmt.setString(2, z_num);
							pstmt.execute();
						}

						JOptionPane.showMessageDialog(this, "����ͣ�����շ�"
								+ allmoney + "Ԫ!");
						JOptionPane.showMessageDialog(this,
								"���������ɹ�����л����ʹ�ã�ף��һ·ƽ����");
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(this, "���Ŵ���");
					}
				} catch (SQLException | ParseException e1) {
					e1.printStackTrace();
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}

		if (e.getActionCommand() == "����") {
			num.setText("");
			car.setText("");
		}

		if (e.getActionCommand() == "�˳�") {
			this.dispose();
		}
	}
}
