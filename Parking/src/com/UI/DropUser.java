package com.UI;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author ������ ����Ϊɾ��ͣ�����û���
 */

public class DropUser extends JInternalFrame implements ActionListener {
	private JLabel tit1 = new JLabel("���ţ�");
	private JLabel tit2 = new JLabel("���룺");
	private TextField num = new TextField(200);
	private TextField password = new TextField(200);
	JButton submit = new JButton("�ύ");
	JButton quit = new JButton("�˳�");
	JButton reset = new JButton("����");

	public DropUser() {
		setTitle("ͣ������ֵ");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		tit1.setBounds(20, 80, 100, 30);
		this.add(tit1);
		num.setBounds(120, 80, 180, 25);
		this.add(num);

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
			String z_num = num.getText().trim();
			num.setText("");
			String z_pass = password.getText().trim();
			password.setText("");

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
					String search = "select ���� from card where ����=?";//�жϿ����Ƿ���ȷ
					pstmt = conn.prepareStatement(search);
					pstmt.setString(1, z_num);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						String search1 = "select ���� from enter where ����=?";//�жϸ�ͣ�����Ƿ�����ʹ��
						pstmt = conn.prepareStatement(search1);
						pstmt.setString(1, z_num);
						rs = pstmt.executeQuery();
						if (rs.next()) {
							JOptionPane.showMessageDialog(this, "��ͣ��������ʹ���У�����������");
						} else {
							String delete = "delete from card where ����=?";
							pstmt = conn.prepareStatement(delete);
							pstmt.setString(1, z_num);
							pstmt.execute();
							JOptionPane.showMessageDialog(this, "ɾ���˺ųɹ���");
							this.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(this, "���Ż��������");
					}
				} catch (SQLException e1) {
					System.out.println("����1");
				}
			} catch (ClassNotFoundException e1) {
				System.out.println("����2");
			}
		}

		if (e.getActionCommand() == "����") {
			num.setText("");
			password.setText("");
		}

		if (e.getActionCommand() == "�˳�") {
			this.dispose();
		}
	}
}
