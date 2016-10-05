package com.UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.BLL.HistoryFrameBll;
import com.BLL.MyHistoryFrameBll;
import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author ������ 
 * ����Ϊͣ�����û���ѯͣ����ʷ��¼��
 */

public class MyHistory extends JInternalFrame implements ActionListener {

	private JButton btnUpdate = new JButton("����");
	private JButton btnSearch = new JButton("��ѯ");
	private JLabel tit = new JLabel("������Ҫ��ѯ��ʱ��Σ�ʱ���ʽΪ yyyy-MM-dd HH:mm:ss");
	private JLabel tit1 = new JLabel("���� 2012-01-01 01:01:01");
	private JLabel tit2 = new JLabel("��ʼʱ�䣺");
	private JLabel tit3 = new JLabel("����ʱ�䣺");
	private TextField time1 = new TextField("2012-01-01 01:01:01",100);
	private TextField time2 = new TextField("2012-01-01 01:01:01",100);
	private JTable jtable;
	String z_time1 = null;
	String z_time2 = null;
	private JScrollPane scrollPane = new JScrollPane();
	MyHistoryFrameBll hb = new MyHistoryFrameBll();
	String num = null;
	private Connection conn; // ���ݿ����Ӷ���
	private Statement stmt; // ���ִ�ж���
	private ResultSet rset; // �����

	public MyHistory(String num) {
		this.num = num;
		setTitle("ͣ����¼");
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
		btnUpdate.setBounds(220, 280, 100, 30);
		btnUpdate.addActionListener(this);
		btnSearch.setBounds(50, 280, 100, 30);
		btnSearch.addActionListener(this);
		p.add(tit);
		p.add(tit1);
		p.add(tit2);
		p.add(tit3);
		p.add(time1);
		p.add(time2);
		p.add(btnSearch);
		p.add(btnUpdate);

		try {
			jtable = hb.getUsers(num);
		} catch (Exception e) {
			System.out.println(e.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ

		}
		
		System.out.println(num);
		jtable.disable();
		scrollPane.setViewportView(jtable);
		this.getContentPane().add(scrollPane);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "����") {
			try {
				jtable = hb.getUsers(num);
			} catch (Exception e1) {
				System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
			}
			scrollPane.setViewportView(jtable);
			this.getContentPane().add(scrollPane);
		}
		
		if (e.getActionCommand() == "��ѯ") {
			z_time1 = time1.getText();
			z_time2 = time2.getText();
			try {
				jtable = hb.getUsers1(num,z_time1,z_time2);
			} catch (Exception e1) {
				System.out.println(e1.toString()); // ���Գ���ʱ��������쳣�������ô˾俴���쳣��Ϣ
			}
			scrollPane.setViewportView(jtable);
			this.getContentPane().add(scrollPane);
		}
	}
}
