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
 * @author 乔至威 
 * 本类为停车场管理系统主界面类
 */

public class MdiFrame extends JFrame implements ActionListener {
	public static User user = new User();
	private MDIDesktopPane desktop = new MDIDesktopPane();

	private JMenuBar menuBar = new JMenuBar();

	private JMenu mnuPark = new JMenu("车场管理");
	private JMenu mnuQuery = new JMenu("查询");
	private JMenu mnuMyinfo = new JMenu("我的信息");
	private JMenu mnuSys = new JMenu("系统管理");
	private JMenu mnuhelp = new JMenu("停车须知");

	private JMenuItem mnuParkIn = new JMenuItem("车辆入场");
	private JMenuItem mnuParkOut = new JMenuItem("车辆出场");
	private JMenuItem mnuQueryUser = new JMenuItem("用户查询");
	private JMenuItem mnuQueryPark = new JMenuItem("车位查询");
	private JMenuItem mnuQueryAll = new JMenuItem("在场查询");
	private JMenuItem mnuQueryHistory = new JMenuItem("停车场历史记录");
	private JMenuItem mnuQueryCharge = new JMenuItem("充值记录");
	//private JMenuItem mnuQueryMoney = new JMenuItem("收入统计");
	private JMenuItem mnuPassword = new JMenuItem("密码修改");
	private JMenuItem mnumyMoney = new JMenuItem("账户余额");
	private JMenuItem mnumyHistory = new JMenuItem("停车记录");
	private JMenuItem mnuOpen = new JMenuItem("开户");
	private JMenuItem mnuCharge = new JMenuItem("充值");
	private JMenuItem mnuClose = new JMenuItem("销户");
	private JMenuItem mnuKnow = new JMenuItem("停车场守则");

	private JScrollPane scrollPane = new JScrollPane();

	public MdiFrame() {
		setMenu();

		setTitle("停车场管理系统");
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
		if (ae.getActionCommand().equals("车辆入场")) {
			desktop.add(new In());
		}
		if (ae.getActionCommand().equals("车辆出场")) {
			desktop.add(new Out());
		}
		if (ae.getActionCommand().equals("车位查询")) {
			desktop.add(new ParkUnitFrame());
		}
		if (ae.getActionCommand().equals("用户查询")) {
			desktop.add(new UserUnitFrame());
		}
		if (ae.getActionCommand().equals("开户")) {
			desktop.add(new Register());
		}
		if (ae.getActionCommand().equals("销户")) {
			desktop.add(new DropUser());
		}
		if (ae.getActionCommand() == "密码修改") {
			desktop.add(new ExPassword());
		}
		if (ae.getActionCommand() == "充值") {
			desktop.add(new Charge());
		}
		if (ae.getActionCommand() == "停车场历史记录") {
			desktop.add(new History());
		}
		if (ae.getActionCommand() == "在场查询") {
			desktop.add(new QueryEnter());
		}
		if (ae.getActionCommand() == "充值记录") {
			desktop.add(new QueryCharge());
		}
		if (ae.getActionCommand() == "停车记录") {
			desktop.add(new MyHistory(user.getNo().trim()));
		}
		if (ae.getActionCommand() == "收入统计") {
			desktop.add(new QueryMoney());
		}	
		if (ae.getActionCommand() == "账户余额") {
			desktop.add(new Mymoney(user.getNo().trim()));
		}
		if (ae.getActionCommand() == "停车场守则") {
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

		if (user.getUserType().equals("用户")) {
			mdiMain.disMenu();
		}
	}
}
