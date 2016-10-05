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
 * 本类为用户修改密码类
 */

public class ExPassword extends JInternalFrame implements ActionListener {
	private JLabel waring = new JLabel("请牢记您的密码！");
	private JLabel tit = new JLabel("卡号");
	private JLabel tit1 = new JLabel("原密码:");
	private JLabel tit2 = new JLabel("新密码:");
	private JLabel tit3 = new JLabel("重新输入密码:");
	TextField num = new TextField(200);
	TextField oldpass = new TextField(200);
	TextField newpass = new TextField(200);
	TextField renewpass = new TextField(200);
	JButton submit = new JButton("确认");
	JButton quit = new JButton("取消");
	JButton reset = new JButton("重置");

	public ExPassword() {
		setTitle("密码修改");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		waring.setBounds(270, 10, 300, 50);
		waring.setFont(new Font("黑体", 1, 20));
		this.add(waring);

		tit.setBounds(20, 80, 100, 30);
		this.add(tit);
		num.setBounds(120, 80, 180, 25);
		this.add(num);

		tit1.setBounds(20, 120, 100, 30);
		this.add(tit1);
		oldpass.setBounds(120, 120, 180, 25);
		this.add(oldpass);

		tit2.setBounds(20, 160, 100, 30);
		this.add(tit2);
		newpass.setBounds(120, 160, 180, 25);
		this.add(newpass);

		tit3.setBounds(20, 200, 100, 30);
		this.add(tit3);
		renewpass.setBounds(120, 200, 180, 25);
		this.add(renewpass);

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
		if (e.getActionCommand() == "确认") {
			String z_old = oldpass.getText();
			String z_new = newpass.getText();
			String z_renew = renewpass.getText();
			String z_num = num.getText();

			boolean isequal = z_renew.trim().equals(z_new.trim());

			if (!isequal) {
				JOptionPane.showMessageDialog(this, "两次输入的密码不一致！");//判断两次输入密码是否一致
				renewpass.setText("");
			}

			if (z_new.equals(z_renew)) {
				String URL = "jdbc:sqlserver://localhost:1433;databaseName=Parking";
				String name = "sa";
				String password = "123456";

				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				PreparedStatement pstmt = null;
				PreparedStatement pstmt2 = null;

				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					try {
						conn = DriverManager.getConnection(URL, name, password);
						String serch = "select 密码 from card where 卡号=? and 密码=?";//取原密码
						pstmt = conn.prepareStatement(serch);
						pstmt.setString(1, z_num);
						pstmt.setString(2, z_old);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							String update = "update card set 密码=? where 卡号=?";
							pstmt2 = conn.prepareStatement(update);
							pstmt2.setInt(1, Integer.parseInt(z_new));
							pstmt2.setString(2, z_num);
							pstmt2.execute();
							JOptionPane.showMessageDialog(this,
									"修改成功，请使用新密码重新登录！");

							this.dispose();
							MdiFrame mdiMain = new MdiFrame();
							new LoginFrame(mdiMain, true);
						} else {
							JOptionPane.showMessageDialog(this,
									"你输入的原密码不正确，请重新输入！");
							oldpass.setText("");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "新密码两次输入不相同！");
			newpass.setText("");
			renewpass.setText("");
		}

		if (e.getActionCommand() == "重置") {
			oldpass.setText("");
			newpass.setText("");
			renewpass.setText("");
		}

		if (e.getActionCommand() == "取消") {
			this.dispose();
		}
	}
}
