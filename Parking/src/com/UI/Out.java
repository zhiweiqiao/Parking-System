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
 * @author 乔至威 本类为停车场汽车出场类
 */

public class Out extends JInternalFrame implements ActionListener {
	private JLabel tit1 = new JLabel("卡号：");
	private JLabel tit2 = new JLabel("车牌号：");
	private TextField num = new TextField(200);
	private TextField car = new TextField(200);
	JButton submit = new JButton("提交");
	JButton quit = new JButton("退出");
	JButton reset = new JButton("重置");
	double price = 2.0;
	double price1 = 1.5;

	public Out() {
		setTitle("车辆出场");
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

		if (e.getActionCommand() == "提交") {
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
					String check = "select 卡号 from card where 卡号=?";
					pstmt = conn.prepareStatement(check);
					pstmt.setString(1, z_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						conn = DriverManager.getConnection(URL, userName,
								password);
						String check1 = "select 停车卡类型 from card where 卡号=?";
						pstmt = conn.prepareStatement(check1);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							z_type = rs.getString(1).trim();
						}
						conn = DriverManager.getConnection(URL, userName,
								password);

						String search = "select 停车时间 from enter where 卡号=?";
						pstmt = conn.prepareStatement(search);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							z_oldtime = rs.getString(1).trim();
						} else {
							JOptionPane
									.showMessageDialog(this, "对不起，本停车卡没有停车！");
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
						if (z_type.trim().equals("优惠")) {
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

						String search1 = "select 车位号 from enter where 卡号=?";
						pstmt = conn.prepareStatement(search1);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							z_park = rs.getString(1);
						}

						String delete = "delete from enter where 卡号=?";
						pstmt = conn.prepareStatement(delete);
						pstmt.setString(1, z_num);
						pstmt.execute();

						String insert = "insert into history values(?,?,?,?,?)";
						pstmt = conn.prepareStatement(insert);
						pstmt.setString(1, z_num);
						pstmt.setString(2, z_car);
						pstmt.setString(3, z_park);
						pstmt.setString(4, z_time.getTime());
						pstmt.setString(5, "出场");
						pstmt.execute();

						String search2 = "select 余额 from card where 卡号=?";
						pstmt = conn.prepareStatement(search2);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							double oldmoney = Double.parseDouble(rs
									.getString(1));
							double newmoney = oldmoney - allmoney;
							z_newmoney = String.valueOf(newmoney);
							String update = "update card set 余额=? where 卡号=?";
							pstmt = conn.prepareStatement(update);
							pstmt.setString(1, z_newmoney);
							pstmt.setString(2, z_num);
							pstmt.execute();
						}

						JOptionPane.showMessageDialog(this, "本次停车共收费"
								+ allmoney + "元!");
						JOptionPane.showMessageDialog(this,
								"车辆出场成功，感谢您的使用，祝您一路平安！");
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(this, "卡号错误！");
					}
				} catch (SQLException | ParseException e1) {
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
