package com.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.UI.QueryJFrame;
import com.Common.*;
import com.DAO.User;

/**
 * @author ������ 
 * ����Ϊͣ��������ϵͳ��������
 */

public class MdiFrame extends JFrame implements ActionListener {
	public static User user = new User();
	private MDIDesktopPane desktop = new MDIDesktopPane();

	private JMenuBar menuBar = new JMenuBar();

	private JMenu mnuPark = new JMenu("��������");
	private JMenu mnuQuery = new JMenu("��ѯ");
	private JMenu mnuMyinfo = new JMenu("�ҵ���Ϣ");
	private JMenu mnuSys = new JMenu("ϵͳ����");
	private JMenu mnuhelp = new JMenu("ͣ����֪");

	private JMenuItem mnuParkIn = new JMenuItem("�����볡");
	private JMenuItem mnuParkOut = new JMenuItem("��������");
	private JMenuItem mnuQueryUser = new JMenuItem("�û���ѯ");
	private JMenuItem mnuQueryPark = new JMenuItem("��λ��ѯ");
	private JMenuItem mnuQueryAll = new JMenuItem("�ڳ���ѯ");
	private JMenuItem mnuQueryHistory = new JMenuItem("ͣ������ʷ��¼");
	private JMenuItem mnuQueryCharge = new JMenuItem("��ֵ��¼");
	//private JMenuItem mnuQueryMoney = new JMenuItem("����ͳ��");
	private JMenuItem mnuPassword = new JMenuItem("�����޸�");
	private JMenuItem mnumyMoney = new JMenuItem("�˻����");
	private JMenuItem mnumyHistory = new JMenuItem("ͣ����¼");
	private JMenuItem mnuOpen = new JMenuItem("����");
	private JMenuItem mnuCharge = new JMenuItem("��ֵ");
	private JMenuItem mnuClose = new JMenuItem("����");
	private JMenuItem mnuKnow = new JMenuItem("ͣ��������");

	private JScrollPane scrollPane = new JScrollPane();

	public MdiFrame() {
		setMenu();

		setTitle("ͣ��������ϵͳ");
		desktop.setBackground(java.awt.Color.lightGray);
		scrollPane.getViewport().add(desktop);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(200, 10, 900, 600);
		this.setVisible(true);
	}

	private void setMenu() {
		mnuPark.add(mnuParkIn);
		mnuParkIn.addActionListener(this);
		mnuPark.add(mnuParkOut);
		mnuParkOut.addActionListener(this);

		mnuQuery.add(mnuQueryPark);
		mnuQueryUser.addActionListener(this);
		mnuQuery.add(mnuQueryUser);
		mnuQueryPark.addActionListener(this);
		mnuQuery.add(mnuQueryHistory);
		mnuQueryHistory.addActionListener(this);
		mnuQuery.add(mnuQueryAll);
		mnuQueryAll.addActionListener(this);
		mnuQuery.add(mnuQueryCharge);
		mnuQueryCharge.addActionListener(this);
		//mnuQuery.add(mnuQueryMoney);
		//mnuQueryMoney.addActionListener(this);
		
		mnuMyinfo.add(mnuPassword);
		mnuPassword.addActionListener(this);
		mnuMyinfo.add(mnumyMoney);
		mnumyMoney.addActionListener(this);

		mnuMyinfo.add(mnumyHistory);
		mnumyHistory.addActionListener(this);

		mnuSys.add(mnuOpen);
		mnuOpen.addActionListener(this);
		mnuSys.add(mnuCharge);
		mnuCharge.addActionListener(this);
		mnuSys.add(mnuClose);
		mnuClose.addActionListener(this);

		mnuhelp.add(mnuKnow);
		mnuKnow.addActionListener(this);

		menuBar.add(mnuPark);
		menuBar.add(mnuQuery);
		menuBar.add(mnuMyinfo);
		menuBar.add(mnuSys);
		menuBar.add(mnuhelp);

		setJMenuBar(menuBar);
	}

	private void disMenu() {
		mnuQueryUser.setEnabled(false);
		mnuSys.setEnabled(false);
		mnuQueryHistory.setEnabled(false);
		mnuQueryAll.setEnabled(false);
		mnuQueryCharge.setEnabled(false);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("�����볡")) {
			desktop.add(new In());
		}
		if (ae.getActionCommand().equals("��������")) {
			desktop.add(new Out());
		}
		if (ae.getActionCommand().equals("��λ��ѯ")) {
			desktop.add(new ParkUnitFrame());
		}
		if (ae.getActionCommand().equals("�û���ѯ")) {
			desktop.add(new UserUnitFrame());
		}
		if (ae.getActionCommand().equals("����")) {
			desktop.add(new Register());
		}
		if (ae.getActionCommand().equals("����")) {
			desktop.add(new DropUser());
		}
		if (ae.getActionCommand() == "�����޸�") {
			desktop.add(new ExPassword());
		}
		if (ae.getActionCommand() == "��ֵ") {
			desktop.add(new Charge());
		}
		if (ae.getActionCommand() == "ͣ������ʷ��¼") {
			desktop.add(new History());
		}
		if (ae.getActionCommand() == "�ڳ���ѯ") {
			desktop.add(new QueryEnter());
		}
		if (ae.getActionCommand() == "��ֵ��¼") {
			desktop.add(new QueryCharge());
		}
		if (ae.getActionCommand() == "ͣ����¼") {
			desktop.add(new MyHistory(user.getNo().trim()));
		}
		if (ae.getActionCommand() == "����ͳ��") {
			desktop.add(new QueryMoney());
		}	
		if (ae.getActionCommand() == "�˻����") {
			desktop.add(new Mymoney(user.getNo().trim()));
		}
		if (ae.getActionCommand() == "ͣ��������") {
			desktop.add(new Know());
		} else {
			System.out.println(user.getName());
		}
		JInternalFrame jf = desktop.getSelectedFrame();

		try {
			jf.setMaximum(true);
		} catch (Exception e) {
			;
		}
	}

	public static void main(String args[]) {
		MdiFrame mdiMain = new MdiFrame();

		new LoginFrame(mdiMain, true);

		if (user.getUserType().equals("�û�")) {
			mdiMain.disMenu();
		}
	}
}
