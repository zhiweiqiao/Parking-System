package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author ������ ����Ϊ��ѯͣ�����û���Ϣ�����ݿ������
 */

public class UsersFrameBll {
	SqlFrameDal ud = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from card"; // card���¼�û�ͣ������Ϣ����card���з��������û���Ϣ
		return ud.getUsers(sql);
	}
	
	public JTable getUsers1(String num) throws Exception {
		String sql = "select * from card where ���� = '" + num + "'";
		return ud.getUsers(sql);		
	}
	
	public JTable getUsers2(String name) throws Exception {
		String sql = "select * from card where ���� = '" + name + "'"; // card���¼�û�ͣ������Ϣ����card���з��������û���Ϣ
		return ud.getUsers(sql);
	}
	
	public JTable getUsers3(String money) throws Exception {
		String sql = "select * from card where ��� < '" + money + "'"; // card���¼�û�ͣ������Ϣ����card���з��������û���Ϣ
		return ud.getUsers(sql);
	}
	
	public JTable getUsers4(String cardtype) throws Exception {
		String sql = "select * from card where ͣ�������� = '" + cardtype + "'"; // card���¼�û�ͣ������Ϣ����card���з��������û���Ϣ
		return ud.getUsers(sql);
	}
	
	public JTable getUsers5(String usertype) throws Exception {
		String sql = "select * from card where �û����� = '" + usertype + "'"; // card���¼�û�ͣ������Ϣ����card���з��������û���Ϣ
		return ud.getUsers(sql);
	}
}
