package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import com.DAL.SqlFrameDal;

public class SearchChargeFrameBll {

	SqlFrameDal cd = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from charge"; // charge���¼�û���ֵ��¼����charge���з��������û���ֵ��¼
		return cd.getUsers(sql);
	}
	
	public JTable getUsers1(String date1,String date2) throws Exception {
		String sql = "select * from charge where ʱ�� > '" + date1 +"' and ʱ�� < '" + date2 + "'";
		 //��history���з���ָ��ʱ��εĳ�����ʷͣ����¼
		return cd.getUsers(sql);
		}
	
	public JTable getUsers2(String num,String date1,String date2) throws Exception {
		String sql = "select * from charge where ʱ�� > '" + date1 +"' and ʱ�� < '" + date2 + "' and ���� ='" + num +"'";
		 //��history���з���ָ��ʱ��εĳ�����ʷͣ����¼
		return cd.getUsers(sql);
		}
}
