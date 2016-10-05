package com.UI;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

import com.DAL.*;

import com.DAO.User;

/**
 * @author 乔至威
 *  本类为登陆界面类
 */

public class LoginFrame extends JDialog implements ActionListener {
	private JTextField Card = new JTextField(10);
	private JTextField PW = new JTextField(10);
	private JLabel card = new JLabel("卡号:");
	private JLabel pw = new JLabel("密码:");
	private JButton btnOK = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JButton btnReset = new JButton("重置");

	public LoginFrame(JFrame jf, boolean model) {
		super(jf, model);
		this.setTitle("用户登录");

		this.setBounds(500, 200, 220, 190);

		getContentPane().setLayout(new FlowLayout(1, 25, 10));

		getContentPane().add(card);
		getContentPane().add(Card);
		getContentPane().add(pw);
		getContentPane().add(PW);

		getContentPane().add(btnOK);
		btnOK.addActionListener(this);

		getContentPane().add(btnCancel);
		btnCancel.addActionListener(this);

		getContentPane().add(btnReset);
		btnReset.addActionListener(this);

		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "确定") {
			String card_t = Card.getText();
			String password_t = PW.getText();

			ResultSet rs;

			SqlExecute se = new SqlExecute();
			try {
				String sql = "select * from card where 卡号 ='" + card_t + "'";
				rs = se.getResultSet(sql);
				User u = new User();
				while (rs.next()) {
					u.setNo(rs.getString("卡号"));
					u.setName(rs.getString(2));
					u.setPassword(rs.getString(3));
					u.setUserType(rs.getString(7));
				}
				rs.close();
				se.closeAll();
				boolean isPass = PW.getText().trim()
						.equals(u.getPassword().trim());
				if (isPass) {
					MdiFrame.user.setUserType(u.getUserType().trim());
					MdiFrame.user.setNo(u.getNo().trim());
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "用户名或密码不正确！\r\n请重新填写。");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("连接失败！");
			}
		} else if (e.getActionCommand() == "取消") {
			System.exit(0);
		} else if (e.getActionCommand() == "重置") {
			Card.setText("");
			PW.setText("");
		}
	}
}
