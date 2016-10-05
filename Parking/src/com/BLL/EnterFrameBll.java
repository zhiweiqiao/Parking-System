package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.DAL.SqlFrameDal;

/**
 * @author 乔至威 本类为查询停车场在场信息的数据库操作类
 */

public class EnterFrameBll {

	SqlFrameDal hd = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from enter"; // enter表记录车辆入场记录，从enter表中返回当前车辆在场信息
		return hd.getUsers(sql);
	}
}
