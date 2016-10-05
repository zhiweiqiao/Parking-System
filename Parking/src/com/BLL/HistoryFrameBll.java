package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.DAL.SqlFrameDal;
import com.DAO.User;

/**
 * @author 乔至威 本类为查询停车场所有用户历史停车的数据库操作类
 */

public class HistoryFrameBll {

	SqlFrameDal hd = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from history"; // history表记录车辆出入场记录，从history表中返回所有用户车辆历史记录
		return hd.getUsers(sql);
	}
	
	public JTable getUsers1(String date1,String date2) throws Exception {
		String sql = "select * from history where 时间 > '" + date1 +"' and 时间 < '" + date2 + "'";
		 //从history表中返回指定时间段的车辆历史停车记录
		return hd.getUsers(sql);
		}	
	
	public JTable getUsers2(String num,String date1,String date2) throws Exception {
		String sql = "select * from history where 时间 > '" + date1 +"' and 时间 < '" + date2 + "' and 卡号 = '" + num + "'";
		 //从history表中返回指定时间段的车辆历史停车记录
		return hd.getUsers(sql);
		}	
	
	public JTable getUsers3(String num,String date1,String date2) throws Exception {
		String sql = "select * from history where 时间 > '" + date1 +"' and 时间 < '" + date2 + "' and 车牌号 = '" + num + "'";
		 //从history表中返回指定时间段的车辆历史停车记录
		return hd.getUsers(sql);
		}	
	
	public JTable getUsers4(String num,String date1,String date2) throws Exception {
		String sql = "select * from history where 时间 > '" + date1 +"' and 时间 < '" + date2 + "' and 车位号 = '" + num + "'";
		 //从history表中返回指定时间段的车辆历史停车记录
		return hd.getUsers(sql);
		}	
}
