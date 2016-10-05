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
 * @author ������
 *  ����Ϊ��ѯ�ڳ�������Ϣ��
 */

public class QueryEnter extends JInternalFrame implements ActionListener {

	private JButton btnUpdate;
	private JTable jtable;
	private JScrollPane scrollPane = new JScrollPane();
	EnterFrameBll hb = new EnterFrameBll();

	public QueryEnter() {
		setTitle("�ڳ���ѯ");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		btnUpdate = new JButton("����");
		btnUpdate.addActionListener(this);
		this.getContentPane().add(btnUpdate, "North");

		try {
			jtable = hb.getUsers();
		} catch (Exception e) {
			System.out.println(e.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ

		}
		
		jtable.disable();
		scrollPane.setViewportView(jtable);
		this.getContentPane().add(scrollPane);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "����") {
			try {
				jtable = hb.getUsers();
			} catch (Exception e1) {
				System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
			}
			scrollPane.setViewportView(jtable);
			this.getContentPane().add(scrollPane);
		}
	}
}
