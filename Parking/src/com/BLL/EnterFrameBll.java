package com.BLL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import com.DAL.SqlFrameDal;

/**
 * @author ������ ����Ϊ��ѯͣ�����ڳ���Ϣ�����ݿ������
 */

public class EnterFrameBll {

	SqlFrameDal hd = new SqlFrameDal();

	public JTable getUsers() throws Exception {
		String sql = "select * from enter"; // enter���¼�����볡��¼����enter���з��ص�ǰ�����ڳ���Ϣ
		return hd.getUsers(sql);
	}
}
