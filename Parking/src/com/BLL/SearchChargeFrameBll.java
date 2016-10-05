package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import com.DAL.SqlFrameDal;

public class SearchChargeFrameBll {

	SqlFrameDal cd = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from charge"; // charge表记录用户充值记录，从charge表中返回所有用户充值记录
		return cd.getUsers(sql);
	}
	
	public JTable getUsers1(String date1,String date2) throws Exception {
		String sql = "select * from charge where 时间 > '" + date1 +"' and 时间 < '" + date2 + "'";
		 //从history表中返回指定时间段的车辆历史停车记录
		return cd.getUsers(sql);
		}
	
	public JTable getUsers2(String num,String date1,String date2) throws Exception {
		String sql = "select * from charge where 时间 > '" + date1 +"' and 时间 < '" + date2 + "' and 卡号 ='" + num +"'";
		 //从history表中返回指定时间段的车辆历史停车记录
		return cd.getUsers(sql);
		}
}
