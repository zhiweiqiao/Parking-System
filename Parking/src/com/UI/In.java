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
 * @author 乔至威 
 * 本类为停车卡汽车进场类
 */

public class In extends JInternalFrame implements ActionListener {
	private JLabel tit1 = new JLabel("卡号：");
	private JLabel tit2 = new JLabel("车牌号：");
	private JLabel tit3 = new JLabel("车辆大小：");
	private TextField num = new TextField(200);
	private TextField car = new TextField(200);
	private JRadioButton big = new JRadioButton("大", true);
	private JRadioButton middle = new JRadioButton("中");
	private JRadioButton small = new JRadioButton("小");
	ButtonGroup bgroup = new ButtonGroup();
	JButton submit = new JButton("提交");
	JButton quit = new JButton("退出");
	JButton reset = new JButton("重置");

	public In() {
		setTitle("车辆入场");
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
		if (e.getActionCommand() == "提交") {
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
					String check = "select 卡号 from card where 卡号=?";
					pstmt = conn.prepareStatement(check);
					pstmt.setString(1, z_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						conn = DriverManager.getConnection(URL, name, password);
						String check1 = "select 卡号 from enter where 卡号=?";//判断停车卡是否正在使用
						pstmt = conn.prepareStatement(check1);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(this, "本停车卡正在使用中！");
						} else {
							conn = DriverManager.getConnection(URL, name,
									password);
							String search = "select 余额 from card where 卡号=?";
							pstmt = conn.prepareStatement(search);
							pstmt.setString(1, z_num);
							rs = pstmt.executeQuery();
							if (rs.next()) {
								mymoney = rs.getString(1);
							}
							double z_money = Double.parseDouble(mymoney.trim());
							if (z_money < 10) {
								JOptionPane.showMessageDialog(this,
										"对不起，您的账户余额小于5小时的停车费用，禁止入场！");
								this.dispose();
							} else {
								conn = DriverManager.getConnection(URL, name,
										password);
								String search1 = "select 车位号 from park where 车位大小 =? and 是否空闲 =?";
								pstmt = conn.prepareStatement(search1);
								pstmt.setString(1, z_size);
								pstmt.setString(2, "是");
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
									pstmt.setString(5, "入场");
									pstmt.execute();
									JOptionPane
											.showMessageDialog(this, "停车成功！");
									JOptionPane.showMessageDialog(this,
											"您的车位号为 " + z_park.trim()
													+ " ，请到指定位置停车！");
									this.dispose();
								} else {
									JOptionPane.showMessageDialog(this,
											"对不起，停车场车位已满！");
									this.dispose();
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(this,
								"停车卡号不正确！\r\n请重新填写。");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand() == "重置") {
			num.setText("");
			car.setText("");
		}

		if (e.getActionCommand() == "退出") {
			this.dispose();
		}
	}
}