package controllers;

import javax.servlet.http.HttpServletRequest;
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
@SessionAttributes("user")
public class LoginController {
	
	@RequestMapping(value="/Login", method = RequestMethod.GET)
	public String showLogin(ModelMap model){
		model.addAttribute("wrongUsername", false);
	    model.addAttribute("wrongPassword", false);
		return "Login";
	}
	
	@RequestMapping(value="/Login", method = RequestMethod.POST)
	public String handleLogin(ModelMap model,HttpServletRequest request){
		boolean isUserInDB = false;
		model.addAttribute("wrongUsername", false);
	    model.addAttribute("wrongPassword", false);
	    
		if(ValidateData.validateNotEmpty(request.getParameter("username")) && ValidateData.validateNotEmpty(request.getParameter("password")))
		{
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
			
			if(usersDao.checkUsernameInDB(request.getParameter("username")))
			{
				if(usersDao.checkPasswordInDB(request.getParameter("username"), request.getParameter("password")))
				{
					isUserInDB = true;
					HttpSession session = request.getSession();
					session.setAttribute("user", usersDao.getLoggedUser(request.getParameter("username")));
					session.setAttribute("username", request.getParameter("username"));
					
					System.out.println(usersDao.getLoggedUser(request.getParameter("username")));
					
				}
				else{
					model.addAttribute("wrongPassword", true);
					//System.out.println("wrong pass");
				}
			}
			else{
				//System.out.println("wrong user");
				model.addAttribute("wrongUsername", true);
			}
		}
		if(isUserInDB){
			return "redirect:Lobby";
		}
		return "Login";
	}
	
	
}
