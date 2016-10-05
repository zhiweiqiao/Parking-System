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
 * @author ������ 
 * ����Ϊͣ�����û�ע����
 */

public class Register extends JInternalFrame implements ActionListener {
	private JLabel welcome = new JLabel("��ӭʹ�ñ�ͣ����ϵͳ");
	private JLabel tit1 = new JLabel("����:");
	private JLabel tit2 = new JLabel("����:");
	private JLabel tit3 = new JLabel("��ϵ��ʽ:");
	private JLabel tit4 = new JLabel("ͣ��������:");
	private JLabel tit5 = new JLabel("ͣ������:");
	TextField name = new TextField(200);
	TextField password = new TextField(200);
	TextField phone = new TextField(200);
	TextField cardnum = new TextField(200);
	ButtonGroup bgroup = new ButtonGroup();
	private JRadioButton b1 = new JRadioButton("��ͨ", true);
	private JRadioButton b2 = new JRadioButton("�Ż�");
	JButton submit = new JButton("�ύ");
	JButton quit = new JButton("�˳�");
	JButton reset = new JButton("����");

	public Register() {
		setTitle("����");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		welcome.setBounds(270, 10, 300, 50);
		welcome.setFont(new Font("����", 1, 20));
		this.add(welcome);

		tit1.setBounds(20, 80, 100, 30);
		this.add(tit1);
		name.setBounds(120, 80, 180, 25);
		this.add(name);

		tit2.setBounds(20, 120, 100, 30);
		this.add(tit2);
		password.setBounds(120, 120, 180, 25);
		this.add(password);

		tit3.setBounds(20, 160, 100, 30);
		this.add(tit3);
		phone.setBounds(120, 160, 180, 25);
		this.add(phone);

		tit4.setBounds(20, 200, 100, 30);
		this.add(tit4);
		bgroup.add(b1);
		bgroup.add(b2);
		b1.setBounds(120, 200, 100, 25);
		this.add(b1);
		b2.setBounds(220, 200, 100, 25);
		this.add(b2);

		tit5.setBounds(20, 240, 100, 30);
		this.add(tit5);
		cardnum.setBounds(120, 240, 180, 25);
		this.add(cardnum);

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
		if (e.getActionCommand() == "�ύ") {
			String z_name = name.getText();
			String z_password = password.getText();
			String z_tel = phone.getText();
			String z_type;
			if (b1.isSelected()) {
				z_type = b1.getText();
			} else {
				z_type = b2.getText();
			}
			boolean istype = (z_type.trim().equals("��ͨ"))
					|| (z_type.trim().equals("�Ż�"));
			String z_Num = cardnum.getText();
			int money = 0;

			if (istype) {
				name.setText("");
				password.setText("");
				phone.setText("");
				cardnum.setText("");
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
						String check = "select ���� from card where ���� = ?";
						pstmt = conn.prepareStatement(check);
						pstmt.setString(1, z_Num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(this,
									"�Բ��𣬿����Ѵ��ڣ�ע��ʧ�ܣ�");
						} else {
							String insert = "insert into card values(?,?,?,?,?,?,?)";
							pstmt = conn.prepareStatement(insert);
							pstmt.setString(1, z_Num);
							pstmt.setString(2, z_name);
							pstmt.setString(3, z_password);
							pstmt.setString(4, z_tel);
							pstmt.setLong(5, money);
							pstmt.setString(6, z_type);
							pstmt.setString(7,  "�û�");
							pstmt.execute();
							JOptionPane.showMessageDialog(this, "��ϲ�㣬ע��ɹ���");
							this.dispose();
						}
					} catch (SQLException e1) {
						System.out.print("�������ݿ����");
					}
				} catch (ClassNotFoundException e2) {
					System.out.print("û���ҵ�����");
				}
			}
		}

		if (e.getActionCommand() == "����") {
			name.setText("");
			password.setText("");
			phone.setText("");
			cardnum.setText("");
		}

		if (e.getActionCommand() == "�˳�") {
			this.dispose();
		}
	}
}
