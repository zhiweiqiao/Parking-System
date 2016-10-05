package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author 乔至威 本类为用户查询自己的停车记录的数据库操作类
 */

public class MyHistoryFrameBll {

	SqlFrameDal hd = new SqlFrameDal();

	public JTable getUsers(String num) throws Exception {
		String sql = "select * from history where 卡号  = '" + num + "'"; // history表记录车辆出入场记录，从history表中返回指定用户停车记录
		return hd.getUsers(sql);
	}
	
	public JTable getUsers1(String num,String date1,String date2) throws Exception {
		String sql = "select * from history where 时间 > '" + date1 +"' and 时间 < '" + date2 + "'" +"and 卡号 = '" + num +"'";
		 //从history表中返回指定时间段的车辆历史停车记录
		return hd.getUsers(sql);
		}	
}
