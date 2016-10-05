package com.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

import com.DAL.SqlExecute;
import com.DAO.User;
import com.DAO.MyTime;

import java.util.Date;

/**
 * @author ������ 
 * ����Ϊͣ��������������
 */

public class In extends JInternalFrame implements ActionListener {
	private JLabel tit1 = new JLabel("���ţ�");
	private JLabel tit2 = new JLabel("���ƺţ�");
	private JLabel tit3 = new JLabel("������С��");
	private TextField num = new TextField(200);
	private TextField car = new TextField(200);
	private JRadioButton big = new JRadioButton("��", true);
	private JRadioButton middle = new JRadioButton("��");
	private JRadioButton small = new JRadioButton("С");
	ButtonGroup bgroup = new ButtonGroup();
	JButton submit = new JButton("�ύ");
	JButton quit = new JButton("�˳�");
	JButton reset = new JButton("����");

	public In() {
		setTitle("�����볡");
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

		tit3.setBounds(20, 160, 100, 30);
		this.add(tit3);
		bgroup.add(big);
		bgroup.add(middle);
		bgroup.add(small);
		big.setBounds(120, 160, 50, 25);
		this.add(big);
		middle.setBounds(200, 160, 50, 25);
		this.add(middle);
		small.setBounds(280, 160, 50, 25);
		this.add(small);

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
		if (e.getActionCommand() == "�ύ") {
			String z_size;
			if (big.isSelected()) {
				z_size = big.getText().trim();
			} else if (middle.isSelected()) {
				z_size = middle.getText().trim();
			} else {
				z_size = small.getText().trim();
			}
			String z_num = num.getText().trim();
			String z_car = car.getText().trim();
			MyTime z_time = new MyTime();
			String z_park = null;
			String mymoney = null;

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
					String check = "select ���� from card where ����=?";
					pstmt = conn.prepareStatement(check);
					pstmt.setString(1, z_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						conn = DriverManager.getConnection(URL, name, password);
						String check1 = "select ���� from enter where ����=?";//�ж�ͣ�����Ƿ�����ʹ��
						pstmt = conn.prepareStatement(check1);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(this, "��ͣ��������ʹ���У�");
						} else {
							conn = DriverManager.getConnection(URL, name,
									password);
							String search = "select ��� from card where ����=?";
							pstmt = conn.prepareStatement(search);
							pstmt.setString(1, z_num);
							rs = pstmt.executeQuery();
							if (rs.next()) {
								mymoney = rs.getString(1);
							}
							double z_money = Double.parseDouble(mymoney.trim());
							if (z_money < 10) {
								JOptionPane.showMessageDialog(this,
										"�Բ��������˻����С��5Сʱ��ͣ�����ã���ֹ�볡��");
								this.dispose();
							} else {
								conn = DriverManager.getConnection(URL, name,
										password);
								String search1 = "select ��λ�� from park where ��λ��С =? and �Ƿ���� =?";
								pstmt = conn.prepareStatement(search1);
								pstmt.setString(1, z_size);
								pstmt.setString(2, "��");
								rs = pstmt.executeQuery();
								if (rs.next()) {
									z_park = rs.getString(1);

									String insert = "insert into enter values(?,?,?,?,?)";
									pstmt = conn.prepareStatement(insert);
									pstmt.setString(1, z_num);
									pstmt.setString(2, z_car);
									pstmt.setString(3, z_park);
									pstmt.setString(4, z_size);
									pstmt.setString(5, z_time.getTime());
									pstmt.execute();

									String insert1 = "insert into history values(?,?,?,?,?)";
									pstmt = conn.prepareStatement(insert1);
									pstmt.setString(1, z_num);
									pstmt.setString(2, z_car);
									pstmt.setString(3, z_park);
									pstmt.setString(4, z_time.getTime());
									pstmt.setString(5, "�볡");
									pstmt.execute();
									JOptionPane
											.showMessageDialog(this, "ͣ���ɹ���");
									JOptionPane.showMessageDialog(this,
											"���ĳ�λ��Ϊ " + z_park.trim()
													+ " ���뵽ָ��λ��ͣ����");
									this.dispose();
								} else {
									JOptionPane.showMessageDialog(this,
											"�Բ���ͣ������λ������");
									this.dispose();
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(this,
								"ͣ�����Ų���ȷ��\r\n��������д��");
					}
				} catch (SQLException e1) {
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