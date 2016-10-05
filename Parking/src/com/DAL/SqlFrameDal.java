package com.DAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import com.DAO.User;

/**
 * @author ������
 * ����Ϊ���ݷ��ʲ�����ݿ�����࣬������һЩ���ݿ�Ĳ�������
 */

public class SqlFrameDal
{
	SqlExecute se = new SqlExecute();
	
	public JTable getUsers(String sql) throws Exception
	{
		return se.getTable(sql);
	}
	
	public List<User> getUsers2(String sql) throws Exception 
	{
		List<User> list = new ArrayList<User>();
		ResultSet rs = se.getResultSet(sql);
		
		while (rs.next())
		{ 
			User u = new User();
            u.setNo(rs.getString(1));
            u.setName(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setUserType(rs.getString(4));
            u.setCardType(rs.getString(4));
            list.add(u);
        }
		rs.close();
		se.closeAll();
		return list;
	}
}
