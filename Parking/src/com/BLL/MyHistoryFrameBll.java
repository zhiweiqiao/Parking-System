package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author ������ ����Ϊ�û���ѯ�Լ���ͣ����¼�����ݿ������
 */

public class MyHistoryFrameBll {

	SqlFrameDal hd = new SqlFrameDal();

	public JTable getUsers(String num) throws Exception {
		String sql = "select * from history where ����  = '" + num + "'"; // history���¼�������볡��¼����history���з���ָ���û�ͣ����¼
		return hd.getUsers(sql);
	}
	
	public JTable getUsers1(String num,String date1,String date2) throws Exception {
		String sql = "select * from history where ʱ�� > '" + date1 +"' and ʱ�� < '" + date2 + "'" +"and ���� = '" + num +"'";
		 //��history���з���ָ��ʱ��εĳ�����ʷͣ����¼
		return hd.getUsers(sql);
		}	
}
