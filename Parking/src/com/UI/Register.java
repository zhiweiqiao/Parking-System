package com.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.DAL.SqlExecute;
import com.DAO.User;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

/**
 * @author 乔至威 
 * 本类为停车卡用户注册类
 */

public class Register extends JInternalFrame implements ActionListener {
	private JLabel welcome = new JLabel("欢迎使用本停车场系统");
	private JLabel tit1 = new JLabel("姓名:");
	private JLabel tit2 = new JLabel("密码:");
	private JLabel tit3 = new JLabel("联系方式:");
	private JLabel tit4 = new JLabel("停车卡类型:");
	private JLabel tit5 = new JLabel("停车卡号:");
	TextField name = new TextField(200);
	TextField password = new TextField(200);
	TextField phone = new TextField(200);
	TextField cardnum = new TextField(200);
	ButtonGroup bgroup = new ButtonGroup();
	private JRadioButton b1 = new JRadioButton("普通", true);
	private JRadioButton b2 = new JRadioButton("优惠");
	JButton submit = new JButton("提交");
	JButton quit = new JButton("退出");
	JButton reset = new JButton("重置");

	public Register() {
		setTitle("开户");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		welcome.setBounds(270, 10, 300, 50);
		welcome.setFont(new Font("黑体", 1, 20));
		this.add(welcome);

		tit1.setBounds(20, 80, 100, 30);
		this.add(tit1);
		name.setBounds(120, 80, 180, 25);
		this.add(name);

		tit2.setBounds(20, 120, 100, 30);
		this.add(tit2);
		password.setBounds(120, 120, 180, 25);
		this.add(password);

		tit3.setBounds(20, 160, 100, 30);
		this.add(tit3);
		phone.setBounds(120, 160, 180, 25);
		this.add(phone);

		tit4.setBounds(20, 200, 100, 30);
		this.add(tit4);
		bgroup.add(b1);
		bgroup.add(b2);
		b1.setBounds(120, 200, 100, 25);
		this.add(b1);
		b2.setBounds(220, 200, 100, 25);
		this.add(b2);

		tit5.setBounds(20, 240, 100, 30);
		this.add(tit5);
		cardnum.setBounds(120, 240, 180, 25);
		this.add(cardnum);

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
			String z_name = name.getText();
			String z_password = password.getText();
			String z_tel = phone.getText();
			String z_type;
			if (b1.isSelected()) {
				z_type = b1.getText();
			} else {
				z_type = b2.getText();
			}
			boolean istype = (z_type.trim().equals("普通"))
					|| (z_type.trim().equals("优惠"));
			String z_Num = cardnum.getText();
			int money = 0;

			if (istype) {
				name.setText("");
				password.setText("");
				phone.setText("");
				cardnum.setText("");
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
						String check = "select 卡号 from card where 卡号 = ?";
						pstmt = conn.prepareStatement(check);
						pstmt.setString(1, z_Num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(this,
									"对不起，卡号已存在，注册失败！");
						} else {
							String insert = "insert into card values(?,?,?,?,?,?,?)";
							pstmt = conn.prepareStatement(insert);
							pstmt.setString(1, z_Num);
							pstmt.setString(2, z_name);
							pstmt.setString(3, z_password);
							pstmt.setString(4, z_tel);
							pstmt.setLong(5, money);
							pstmt.setString(6, z_type);
							pstmt.setString(7,  "用户");
							pstmt.execute();
							JOptionPane.showMessageDialog(this, "恭喜你，注册成功！");
							this.dispose();
						}
					} catch (SQLException e1) {
						System.out.print("连接数据库错误");
					}
				} catch (ClassNotFoundException e2) {
					System.out.print("没有找到数据");
				}
			}
		}

		if (e.getActionCommand() == "重置") {
			name.setText("");
			password.setText("");
			phone.setText("");
			cardnum.setText("");
		}

		if (e.getActionCommand() == "退出") {
			this.dispose();
		}
	}
}
