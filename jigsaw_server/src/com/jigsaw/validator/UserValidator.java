package com.jigsaw.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class UserValidator extends Validator {

	protected void validate(Controller controller){
		validateRequiredString("Site_User.LoginName", "用户名", "请输入用户名！");
		validateRequiredString("Site_User.PassWord", "密码", "请输入密码！");
	}
	
	protected void handleError(Controller controller){
		
	}
}
