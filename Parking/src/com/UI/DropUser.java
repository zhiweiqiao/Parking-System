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
 * @author 乔至威 本类为删除停车卡用户类
 */

public class DropUser extends JInternalFrame implements ActionListener {
	private JLabel tit1 = new JLabel("卡号：");
	private JLabel tit2 = new JLabel("密码：");
	private TextField num = new TextField(200);
	private TextField password = new TextField(200);
	JButton submit = new JButton("提交");
	JButton quit = new JButton("退出");
	JButton reset = new JButton("重置");

	public DropUser() {
		setTitle("停车卡充值");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		tit1.setBounds(20, 80, 100, 30);
		this.add(tit1);
		num.setBounds(120, 80, 180, 25);
		this.add(num);

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
					String search = "select 卡号 from card where 卡号=?";//判断卡号是否正确
					pstmt = conn.prepareStatement(search);
					pstmt.setString(1, z_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						String search1 = "select 卡号 from enter where 卡号=?";//判断该停车卡是否正在使用
						pstmt = conn.prepareStatement(search1);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(this, "该停车卡正在使用中，不能销户！");
						} else {
							String delete = "delete from card where 卡号=?";
							pstmt = conn.prepareStatement(delete);
							pstmt.setString(1, z_num);
							pstmt.execute();
							JOptionPane.showMessageDialog(this, "删除账号成功！");
							this.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(this, "卡号或密码错误！");
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
		}

		if (e.getActionCommand() == "退出") {
			this.dispose();
		}
	}
}
