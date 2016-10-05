package com.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.BLL.ParkFrameBll;
import com.BLL.UsersFrameBll;
import com.DAO.User;

/**
 * @author ������ ����Ϊͣ������λ��Ϣ��ѯ������
 */

public class ParkUnitFrame extends JInternalFrame implements ActionListener {

	private JButton btnUpdate = new JButton("����");
	private JButton btnSearch = new JButton("��ѯ");
	private JTable jtable;
	private JLabel tit = new JLabel("��ѡ��Ҫ��ѯ������");
	private JLabel tit1 = new JLabel("��λ��С��");
	private JLabel tit2 = new JLabel("�Ƿ���У�");
	private Object data1[] = { "��", "��", "С" };
	private Object data2[] = { "��", "��"};
	private JComboBox com1 = new JComboBox(data1);
	private JComboBox com2 = new JComboBox(data2);
	private String z_size = null;
	private String z_is = null;

	private JScrollPane scrollPane = new JScrollPane();
	ParkFrameBll ub = new ParkFrameBll();

	public ParkUnitFrame() {
		setTitle("��λ��Ϣ");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		getContentPane().setLayout(new GridLayout(1, 1));
		JPanel p = new JPanel();
		this.getContentPane().add(p);
		this.getContentPane().add(scrollPane);
		p.setLayout(null);
		tit.setBounds(10, 30, 1000, 30);
		tit1.setBounds(10, 100, 100, 25);
		tit2.setBounds(10, 150, 100, 25);
		com1.setBounds(100, 100, 100, 25);
		com1.addActionListener(this);
		com2.setBounds(100, 150, 100, 25);
		com2.addActionListener(this);
		btnUpdate.setBounds(220, 280, 100, 30);
		btnUpdate.addActionListener(this);
		btnSearch.setBounds(50, 280, 100, 30);
		btnSearch.addActionListener(this);
		p.add(tit);
		p.add(tit1);
		p.add(tit2);
		p.add(com1);
		p.add(com2);
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
			z_size = (String) com1.getSelectedItem();
			z_is = (String) com2.getSelectedItem();
			try {
				jtable = ub.getUsers2(z_size,z_is);
			} catch (Exception e1) {
				System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
			}
			scrollPane.setViewportView(jtable);
			this.getContentPane().add(scrollPane);
		}
	}
}
