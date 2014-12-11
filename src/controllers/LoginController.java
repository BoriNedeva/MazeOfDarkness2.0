package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value="/Login", method = RequestMethod.GET)
	public String handleLogin(){
		return "Login";
	}
	
	@RequestMapping(value="/Login", method = RequestMethod.POST)
	public String showLogin(){
		return "Login";
	}
	
	
}
