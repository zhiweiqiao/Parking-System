package com.UI;

import java.awt.*;

import javax.swing.*;

/**
 * @author 乔至威
 *  本类为停车卡停车守则类
 */

public class Know extends JInternalFrame {
	private JLabel welcome = new JLabel("欢迎使用本停车场管理系统");
	private JLabel tit1 = new JLabel("一、本停车场车位分大、中、小三种。");
	private JLabel tit2 = new JLabel("二、进入本停车场停车须提前办理停车卡并充值，停车卡分普通卡和优惠卡两类。");
	private JLabel tit3 = new JLabel(
			"三、一个停车卡可允许不同车辆不同时间使用。但同一时间，一个停车卡只允许一辆汽车使用。");
	private JLabel tit4 = new JLabel(
			"四、停车实行统一收费标准，不区分车位大小，普通卡收费2元/小时，优惠卡收费1.5元/小时。当停车时间不足一小时时，均收取2元。");
	private JLabel tit5 = new JLabel("五、停车卡中余额低于5小时的停车费用，请先充值后再入场。");
	private JLabel tit6 = new JLabel("六、本停车守则最终解释权归本停车场所有。");

	public Know() {
		setTitle("停车场守则");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		welcome.setBounds(270, 10, 300, 50);
		welcome.setFont(new Font("黑体", 1, 20));
		this.add(welcome);

		tit1.setBounds(20, 80, 1000, 30);
		this.add(tit1);
		tit2.setBounds(20, 120, 1000, 30);
		this.add(tit2);
		tit3.setBounds(20, 160, 1000, 30);
		this.add(tit3);
		tit4.setBounds(20, 200, 1000, 30);
		this.add(tit4);
		tit5.setBounds(20, 240, 1000, 30);
		this.add(tit5);
		tit6.setBounds(20, 280, 1000, 30);
		this.add(tit6);

		this.setVisible(true);
	}
}