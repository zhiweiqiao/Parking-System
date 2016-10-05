package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author 乔至威 本类为查询停车场用户信息的数据库操作类
 */

public class UsersFrameBll {
	SqlFrameDal ud = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from card"; // card表记录用户停车卡信息，从card表中返回所有用户信息
		return ud.getUsers(sql);
	}
	
	public JTable getUsers1(String num) throws Exception {
		String sql = "select * from card where 卡号 = '" + num + "'";
		return ud.getUsers(sql);		
	}
	
	public JTable getUsers2(String name) throws Exception {
		String sql = "select * from card where 姓名 = '" + name + "'"; // card表记录用户停车卡信息，从card表中返回所有用户信息
		return ud.getUsers(sql);
	}
	
	public JTable getUsers3(String money) throws Exception {
		String sql = "select * from card where 余额 < '" + money + "'"; // card表记录用户停车卡信息，从card表中返回所有用户信息
		return ud.getUsers(sql);
	}
	
	public JTable getUsers4(String cardtype) throws Exception {
		String sql = "select * from card where 停车卡类型 = '" + cardtype + "'"; // card表记录用户停车卡信息，从card表中返回所有用户信息
		return ud.getUsers(sql);
	}
	
	public JTable getUsers5(String usertype) throws Exception {
		String sql = "select * from card where 用户类型 = '" + usertype + "'"; // card表记录用户停车卡信息，从card表中返回所有用户信息
		return ud.getUsers(sql);
	}
}
