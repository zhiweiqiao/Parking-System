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
 * @author ������
 *  ����Ϊ��½������
 */

public class LoginFrame extends JDialog implements ActionListener {
	private JTextField Card = new JTextField(10);
	private JTextField PW = new JTextField(10);
	private JLabel card = new JLabel("����:");
	private JLabel pw = new JLabel("����:");
	private JButton btnOK = new JButton("ȷ��");
	private JButton btnCancel = new JButton("ȡ��");
	private JButton btnReset = new JButton("����");

	public LoginFrame(JFrame jf, boolean model) {
		super(jf, model);
		this.setTitle("�û���¼");

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
		if (e.getActionCommand() == "ȷ��") {
			String card_t = Card.getText();
			String password_t = PW.getText();

			ResultSet rs;

			SqlExecute se = new SqlExecute();
			try {
				String sql = "select * from card where ���� ='" + card_t + "'";
				rs = se.getResultSet(sql);
				User u = new User();
				while (rs.next()) {
					u.setNo(rs.getString("����"));
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
					JOptionPane.showMessageDialog(this, "�û��������벻��ȷ��\r\n��������д��");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
				System.out.println("����ʧ�ܣ�");
			}
		} else if (e.getActionCommand() == "ȡ��") {
			System.exit(0);
		} else if (e.getActionCommand() == "����") {
			Card.setText("");
			PW.setText("");
		}
	}
}
