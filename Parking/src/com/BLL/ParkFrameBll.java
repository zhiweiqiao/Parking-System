package com.BLL;

import java.sql.*;
import java.util.*;
import javax.swing.*;

import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author ������ ����Ϊ��ѯͣ������ǰ��λ��Ϣ�����ݿ������
 */

public class ParkFrameBll {
	SqlFrameDal pd = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from park"; // park���ų�λ��Ϣ����park���з��ص�ǰ��λ��Ϣ
		return pd.getUsers(sql);
	}
	
	public JTable getUsers1(String num) throws Exception {
		String sql = "select * from park where ��λ�� = '" + num + "'";
		return pd.getUsers(sql);		
	}
	
	public JTable getUsers2(String size,String is) throws Exception {
		String sql = "select * from park where ��λ��С = '" + size + "'" + "and �Ƿ���� = '" + is + "'";
		return pd.getUsers(sql);		
	}
}
