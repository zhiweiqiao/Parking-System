package com.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.BLL.EnterFrameBll;
import com.BLL.HistoryFrameBll;
import com.DAO.User;

/**
 * @author 乔至威
 *  本类为查询在场车辆信息类
 */

public class QueryEnter extends JInternalFrame implements ActionListener {

	private JButton btnUpdate;
	private JTable jtable;
	private JScrollPane scrollPane = new JScrollPane();
	EnterFrameBll hb = new EnterFrameBll();

	public QueryEnter() {
		setTitle("在场查询");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		btnUpdate = new JButton("更新");
		btnUpdate.addActionListener(this);
		this.getContentPane().add(btnUpdate, "North");

		try {
			jtable = hb.getUsers();
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
				jtable = hb.getUsers();
			} catch (Exception e1) {
				System.out.println(e1.toString()); // 调试程序时，如果有异常，可以用此句看看异常信息
			}
			scrollPane.setViewportView(jtable);
			this.getContentPane().add(scrollPane);
		}
	}
}
