package com.UI;

import java.awt.*;

import javax.swing.*;

/**
 * @author ������
 *  ����Ϊͣ����ͣ��������
 */

public class Know extends JInternalFrame {
	private JLabel welcome = new JLabel("��ӭʹ�ñ�ͣ��������ϵͳ");
	private JLabel tit1 = new JLabel("һ����ͣ������λ�ִ��С�С���֡�");
	private JLabel tit2 = new JLabel("�������뱾ͣ����ͣ������ǰ����ͣ��������ֵ��ͣ��������ͨ�����Żݿ����ࡣ");
	private JLabel tit3 = new JLabel(
			"����һ��ͣ����������ͬ������ͬʱ��ʹ�á���ͬһʱ�䣬һ��ͣ����ֻ����һ������ʹ�á�");
	private JLabel tit4 = new JLabel(
			"�ġ�ͣ��ʵ��ͳһ�շѱ�׼�������ֳ�λ��С����ͨ���շ�2Ԫ/Сʱ���Żݿ��շ�1.5Ԫ/Сʱ����ͣ��ʱ�䲻��һСʱʱ������ȡ2Ԫ��");
	private JLabel tit5 = new JLabel("�塢ͣ������������5Сʱ��ͣ�����ã����ȳ�ֵ�����볡��");
	private JLabel tit6 = new JLabel("������ͣ���������ս���Ȩ�鱾ͣ�������С�");

	public Know() {
		setTitle("ͣ��������");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setResizable(true);
		this.getContentPane().setLayout(null);

		welcome.setBounds(270, 10, 300, 50);
		welcome.setFont(new Font("����", 1, 20));
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