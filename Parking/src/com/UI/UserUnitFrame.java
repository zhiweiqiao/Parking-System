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
 * @author ������ ����Ϊͣ�����û���Ϣ��ѯ��
 */

public class UserUnitFrame extends JInternalFrame implements ActionListener {
	private JButton btnUpdate = new JButton("����");
	private JButton btnSearch = new JButton("��ѯ");
	private JTable jtable;
	private JLabel tit = new JLabel("��ѡ��Ҫ��ѯ������");
	private Object data[] = { "����", "����", "���С��", "ͣ��������", "�û�����" };
	private JComboBox com = new JComboBox(data);
	private TextField num = new TextField(50);
	private String z_num = null;

	private JScrollPane scrollPane = new JScrollPane();
	UsersFrameBll ub = new UsersFrameBll();

	public UserUnitFrame() {
		setTitle("�û���Ϣ");
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
				jtable = ub.getUsers();
			} catch (Exception e1) {
				System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
			}
			scrollPane.setViewportView(jtable);
			this.getContentPane().add(scrollPane);
		}

		if (e.getActionCommand() == "��ѯ") {
			z_num = num.getText().trim();
			System.out.println(com.getSelectedItem());
			System.out.println(z_num);
			if (com.getSelectedItem() == "����") {
				try {
					jtable = ub.getUsers1(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else if (com.getSelectedItem() == "����") {
				try {
					jtable = ub.getUsers2(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else if (com.getSelectedItem() == "���С��") {
				try {
					jtable = ub.getUsers3(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else if (com.getSelectedItem() == "ͣ��������") {
				try {
					jtable = ub.getUsers4(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else {
				try {
					jtable = ub.getUsers5(z_num);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			}
		}
	}
}
