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

import com.DAO.MyTime;

/**
 * @author 乔至威 本类为停车卡充值类
 */

public class Charge extends JInternalFrame implements ActionListener {
	private JLabel tit1 = new JLabel("卡号：");
	private JLabel tit3 = new JLabel("充值金额：");
	private TextField num = new TextField(200);
	private TextField password = new TextField(200);
	private TextField money = new TextField(200);
	JButton submit = new JButton("提交");
	JButton quit = new JButton("退出");
	JButton reset = new JButton("重置");

	public Charge() {
		setTitle("停车卡充值");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		tit1.setBounds(20, 80, 100, 25);
		this.add(tit1);
		num.setBounds(120, 80, 180, 25);
		this.add(num);

		tit3.setBounds(20, 120, 100, 25);
		this.add(tit3);
		money.setBounds(120, 120, 180, 25);
		this.add(money);

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
			String z_num = num.getText().trim();
			num.setText("");
			String z_pass = password.getText().trim();
			password.setText("");
			double z_money = Double.parseDouble(money.getText().trim());
			money.setText("");
			MyTime z_time = new MyTime();

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
					if (z_money < 0) {
						JOptionPane.showMessageDialog(this, "充值金额不能为负数！"); // 判断充值金额
					} else {
						conn = DriverManager.getConnection(URL, name, password);
						String search = "select 余额 from card where 卡号=?"; // 判断卡号是否正确
						pstmt = conn.prepareStatement(search);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							double oldmoney = Double.parseDouble(rs
									.getString(1));
							double newmoney = oldmoney + z_money;
							String update = "update card set 余额=? where 卡号=?";// 更新card表余额
							pstmt = conn.prepareStatement(update);
							pstmt.setLong(1, (long) newmoney);
							pstmt.setString(2, z_num);
							pstmt.execute();

							String insert = "insert charge values(?,?,?)";
							pstmt = conn.prepareStatement(insert);
							pstmt.setString(1, z_num);
							pstmt.setLong(2, (long) z_money);
							pstmt.setString(3, z_time.getTime());
							pstmt.execute();

							JOptionPane.showMessageDialog(this, "充值成功！");
							this.dispose();
						} else {
							JOptionPane.showMessageDialog(this, "卡号或密码错误！");
						}
					}
				} catch (SQLException e1) {
					System.out.println("错误1");
				}
			} catch (ClassNotFoundException e1) {
				System.out.println("错误2");
			}
		}

		if (e.getActionCommand() == "重置") {
			num.setText("");
			password.setText("");
			money.setText("");
		}

		if (e.getActionCommand() == "退出") {
			this.dispose();
		}

	}

}
