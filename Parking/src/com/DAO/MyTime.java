package com.DAO;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author 乔至威
 * 本类为时间类，定义了汽时间类的操作方法
 * 主要得到系统当前的时间
 */

public class MyTime 
{
	public String getTime()
	{
		SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return now.format(new Date());
	}
}
