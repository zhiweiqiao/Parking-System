package com.BLL;

import java.sql.*;
import java.util.*;
import javax.swing.*;

import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author 乔至威 本类为查询停车场当前车位信息的数据库操作类
 */

public class ParkFrameBll {
	SqlFrameDal pd = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from park"; // park表存放车位信息，从park表中返回当前车位信息
		return pd.getUsers(sql);
	}
	
	public JTable getUsers1(String num) throws Exception {
		String sql = "select * from park where 车位号 = '" + num + "'";
		return pd.getUsers(sql);		
	}
	
	public JTable getUsers2(String size,String is) throws Exception {
		String sql = "select * from park where 车位大小 = '" + size + "'" + "and 是否空闲 = '" + is + "'";
		return pd.getUsers(sql);		
	}
}
