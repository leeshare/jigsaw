package com.jigsaw.model;

public class UserContext {

	public String SigninID = "";
	public String UserID = "";
	public String UserName = "";
	public String CultureName = "zh-CN";
	public int OrganizationID = 0;
	public String AppCode = "";
	public String IP = "";
	
	public static UserContext CurrentUser;
}
