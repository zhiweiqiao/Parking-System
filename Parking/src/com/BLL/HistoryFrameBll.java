package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author ������ ����Ϊ��ѯͣ���������û���ʷͣ�������ݿ������
 */

public class HistoryFrameBll {

	SqlFrameDal hd = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from history"; // history���¼�������볡��¼����history���з��������û�������ʷ��¼
		return hd.getUsers(sql);
	}
	
	public JTable getUsers1(String date1,String date2) throws Exception {
		String sql = "select * from history where ʱ�� > '" + date1 +"' and ʱ�� < '" + date2 + "'";
		 //��history���з���ָ��ʱ��εĳ�����ʷͣ����¼
		return hd.getUsers(sql);
		}	
	
	public JTable getUsers2(String num,String date1,String date2) throws Exception {
		String sql = "select * from history where ʱ�� > '" + date1 +"' and ʱ�� < '" + date2 + "' and ���� = '" + num + "'";
		 //��history���з���ָ��ʱ��εĳ�����ʷͣ����¼
		return hd.getUsers(sql);
		}	
	
	public JTable getUsers3(String num,String date1,String date2) throws Exception {
		String sql = "select * from history where ʱ�� > '" + date1 +"' and ʱ�� < '" + date2 + "' and ���ƺ� = '" + num + "'";
		 //��history���з���ָ��ʱ��εĳ�����ʷͣ����¼
		return hd.getUsers(sql);
		}	
	
	public JTable getUsers4(String num,String date1,String date2) throws Exception {
		String sql = "select * from history where ʱ�� > '" + date1 +"' and ʱ�� < '" + date2 + "' and ��λ�� = '" + num + "'";
		 //��history���з���ָ��ʱ��εĳ�����ʷͣ����¼
		return hd.getUsers(sql);
		}	
}
