package com.DAO;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ������
 * ����Ϊʱ���࣬��������ʱ����Ĳ�������
 * ��Ҫ�õ�ϵͳ��ǰ��ʱ��
 */

public class MyTime 
{
	public String getTime()
	{
		SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return now.format(new Date());
	}
}
