package controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import player.UsersDAO;
import utilities.ValidateData;

@Controller
public class RegisterController {

	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String showRegister(ModelMap model) {
		model.addAttribute("wrongUsername", false);
		model.addAttribute("takenUsername", false);
		model.addAttribute("wrongPassword", false);
		model.addAttribute("wrongEmail", false);
		model.addAttribute("existEmail", false);
		model.addAttribute("equalsPasswords", false);
		model.addAttribute("successfulRegistration", false);
		return "Register";
	}

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	public String handleRegister(ModelMap model, HttpServletRequest request) {
		boolean areValidated = true;
		boolean isSuccessful = false;
		model.addAttribute("wrongUsername", false);
		model.addAttribute("takenUsername", false);
		model.addAttribute("wrongPassword", false);
		model.addAttribute("wrongEmail", false);
		model.addAttribute("existEmail", false);
		model.addAttribute("equalsPasswords", false);
		model.addAttribute("successfulRegistration", false);
		
		if (ValidateData.validateNotEmpty(request.getParameter("username")) && ValidateData.validateNotEmpty(request.getParameter("password")) && ValidateData.validateNotEmpty(request.getParameter("repeatPassword")) && ValidateData.validateNotEmpty(request.getParameter("email"))) 
		{
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
			
			if(ValidateData.validateUsername(request.getParameter("username")))
			{
				if (usersDao.checkUsernameInDB(request.getParameter("username"))) 
				{
					model.addAttribute("takenUsername", true);
					areValidated = false;
				}
			}
			else {
				model.addAttribute("wrongUsername", true);
				areValidated = false;
			}
			
			if(ValidateData.validateEmail(request.getParameter("email")))
			{
				//8									
				if (usersDao.checkEmailInDB(request.getParameter("email"))) 
				{
					model.addAttribute("existEmail", true);
					areValidated = false;
				}
			}//8
			else {
				model.addAttribute("wrongEmail", true);
				areValidated = false;
			}
			
			if(!ValidateData.validatePassword(request.getParameter("password"))){
				model.addAttribute("wrongPassword", true);
				areValidated = false;
			}
			
			if(!ValidateData.validateConfirmation(request.getParameter("password"), request.getParameter("repeatPassword"))){
				model.addAttribute("equalsPasswords", true);
				areValidated = false;
			}
			
			if (areValidated) {
				
				isSuccessful = usersDao.registerNewUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("email"));
				model.addAttribute("successfulRegistration", isSuccessful);
				//areValidated = false;
			}
		}
		
		return "Register";
	}

}
