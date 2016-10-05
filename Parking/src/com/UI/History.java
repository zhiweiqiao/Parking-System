package com.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.BLL.HistoryFrameBll;
import com.BLL.ParkFrameBll;
import com.DAO.User;

/**
 * @author ������ ����Ϊͣ����ͣ����ʷ��¼��ѯ��
 */

public class History extends JInternalFrame implements ActionListener {

	private JButton btnUpdate = new JButton("����");
	private JButton btnSearch = new JButton("��ѯ");
	private JLabel tit = new JLabel("������Ҫ��ѯ��ʱ��Σ�ʱ���ʽΪ yyyy-MM-dd HH:mm:ss");
	private JLabel tit1 = new JLabel("���� 2012-01-01 01:01:01");
	private JLabel tit2 = new JLabel("��ʼʱ�䣺");
	private JLabel tit3 = new JLabel("����ʱ�䣺");
	private Object data[] = { "����", "���ƺ�", "��λ��" };
	private JComboBox com = new JComboBox(data);
	private TextField time1 = new TextField("2012-01-01 01:01:01", 100);
	private TextField time2 = new TextField("2012-01-01 01:01:01", 100);
	private TextField num = new TextField("null",100);
	String z_num = null;
	private JTable jtable;
	String z_time1 = null;
	String z_time2 = null;
	private JScrollPane scrollPane = new JScrollPane();
	HistoryFrameBll hb = new HistoryFrameBll();

	public History() {
		setTitle("��ʷ��¼");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		getContentPane().setLayout(new GridLayout(1, 1));
		JPanel p = new JPanel();
		this.getContentPane().add(p);
		this.getContentPane().add(scrollPane);
		p.setLayout(null);
		tit.setBounds(10, 10, 1000, 30);
		tit1.setBounds(10, 40, 1000, 30);
		tit2.setBounds(10, 70, 1000, 30);
		time1.setBounds(10, 100, 300, 25);
		tit3.setBounds(10, 170, 1000, 30);
		time2.setBounds(10, 200, 300, 25);
		com.setBounds(10, 300, 100, 25);
		com.addActionListener(this);
		num.setBounds(110, 300, 200, 25);
		btnUpdate.setBounds(220, 400, 100, 30);
		btnUpdate.addActionListener(this);
		btnSearch.setBounds(50, 400, 100, 30);
		btnSearch.addActionListener(this);
		p.add(tit);
		p.add(tit1);
		p.add(tit2);
		p.add(tit3);
		p.add(time1);
		p.add(time2);
		p.add(com);
		p.add(num);
		p.add(btnSearch);
		p.add(btnUpdate);

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

		if (e.getActionCommand() == "��ѯ") {
			z_num = num.getText().trim();
			z_time1 = time1.getText().trim();
			z_time2 = time2.getText().trim();
			System.out.println(z_num);
			if (z_num.equals("null")) {
				try {
					jtable = hb.getUsers1(z_time1, z_time2);
				} catch (Exception e1) {
					System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
				}
				scrollPane.setViewportView(jtable);
				this.getContentPane().add(scrollPane);
			} else {
				if (com.getSelectedItem() == "����") {
					try {
						jtable = hb.getUsers2(z_num, z_time1, z_time2);
					} catch (Exception e1) {
						System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
					}
					scrollPane.setViewportView(jtable);
					this.getContentPane().add(scrollPane);
				} else if (com.getSelectedItem() == "���ƺ�") {
					try {
						jtable = hb.getUsers3(z_num, z_time1, z_time2);
					} catch (Exception e1) {
						System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
					}
					scrollPane.setViewportView(jtable);
					this.getContentPane().add(scrollPane);
				} else {
					try {
						jtable = hb.getUsers4(z_num, z_time1, z_time2);
					} catch (Exception e1) {
						System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
					}
					scrollPane.setViewportView(jtable);
					this.getContentPane().add(scrollPane);
				}
			}
		}
	}
}
