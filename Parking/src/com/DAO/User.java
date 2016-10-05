package com.DAO;

/**
 * @author 乔至威
 * 本类为用户类，定义了用户类的操作方法
 */

public class User
{
	private String No;
	private String name;
	private String password;
	private String usertype;
	private String money;
	private String cardtype;
	
	public String getNo()
	{
		return this.No;
	}
	public void setNo(String No)
	{
		this.No = No;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getUserType()
	{
		return this.usertype;
	}
	public void setUserType(String usertype) 
	{
		this.usertype = usertype;
	}
	
	public String getMoney()
	{
		return this.money;
	}
	public void setMoney(String money)
	{
		this.money = money;
	}
	
	public String getCardType()
	{
		return this.usertype;
	}
	public void setCardType(String cardtype) 
	{
		this.cardtype = cardtype;
	}
}
