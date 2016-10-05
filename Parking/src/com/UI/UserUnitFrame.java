package com.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.BLL.UsersFrameBll;
import com.DAO.User;

/**
 * @author 乔至威 本类为停车场用户信息查询类
 */

public class UserUnitFrame extends JInternalFrame implements ActionListener {
	private JButton btnUpdate = new JButton("更新");
	private JButton btnSearch = new JButton("查询");
	private JTable jtable;
	private JLabel tit = new JLabel("请选择要查询的内容");
	private Object data[] = { "卡号", "姓名", "余额小于", "停车卡类型", "用户类型" };
	private JComboBox com = new JComboBox(data);
	private TextField num = new TextField(50);
	private String z_num = null;

	private JScrollPane scrollPane = new JScrollPane();
	UsersFrameBll ub = new UsersFrameBll();

	public UserUnitFrame() {
		setTitle("用户信息");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		getContentPane().setLayout(new GridLayout(1, 1));
		JPanel p = new JPanel();
		this.getContentPane().add(p);
		this.getContentPane().add(scrollPane);
		p.setLayout(null);
		tit.setBounds(10, 50, 1000, 30);
		com.setBounds(10, 100, 100, 25);
		com.addActionListener(this);
		num.setBounds(110, 100, 300, 25);
		btnUpdate.setBounds(220, 280, 100, 30);
		btnUpdate.addActionListener(this);
		btnSearch.setBounds(50, 280, 100, 30);
		btnSearch.addActionListener(this);
		p.add(tit);
		p.add(com);
		p.add(num);
		p.add(btnSearch);
		p.add(btnUpdate);

		try {
			jtable = ub.getUsers();
		} catch (Exception e) {
			System.out.println(e.toString()); // 调试程序时，如果有异常，可以用此句看看异常信息

		}

		jtable.disable();
		scrollPane.setViewportView(jtable);
		this.getContentPane().add(scrollPane);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "更新") {
			try {
				jtable = ub.getUsers();
			} catch (Exception e1) {
				System.out.println(e1.toString()); // 调试程序时，如果有异常，可以用此句看看异常信息
			}
			scrollPane.setViewportView(jtable);
			this.getContentPane().add(scrollPane);
		}

		if (e.getActionCommand() == "查询") {
			z_num = num.getText().trim();
			System.out.println(com.getSelectedItem());
			System.out.println(z_num);
			if (com.getSelectedItem() == "卡号") {
				try {
					jtable = ub.getUsers1(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // 调试程序时，如果有异常，可以用此句看看异常信息
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else if (com.getSelectedItem() == "姓名") {
				try {
					jtable = ub.getUsers2(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // 调试程序时，如果有异常，可以用此句看看异常信息
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else if (com.getSelectedItem() == "余额小于") {
				try {
					jtable = ub.getUsers3(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // 调试程序时，如果有异常，可以用此句看看异常信息
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else if (com.getSelectedItem() == "停车卡类型") {
				try {
					jtable = ub.getUsers4(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // 调试程序时，如果有异常，可以用此句看看异常信息
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else {
				try {
					jtable = ub.getUsers5(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // 调试程序时，如果有异常，可以用此句看看异常信息
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			}
		}
	}
}
