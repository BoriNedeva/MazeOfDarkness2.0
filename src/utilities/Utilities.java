package utilities;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import player.User;
import player.UsersDAO;

public class Utilities {
	
	public static List<User> getListOfAllUsers()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
		List<User> users = usersDao.getUsers();
		
		return users;
	}
	
	public static User getLoggedUser(List<User> allUsers, String username)
	{
		User loggedUser = null;
		for(User user : allUsers){
			if(user.getUsername().equals(username)){
				loggedUser = user;
				break;
			}
		}
		return loggedUser;
	}
	
	public static User getdaoUser(String username)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
		return usersDao.getLoggedUser(username);
	}
	
	public static boolean checkusernameInDBDAO(String username)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
		return usersDao.checkUsernameInDB(username);
	}
	
	public static boolean checkpasswordInDBDAO(String username, String password)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
		return usersDao.checkPasswordInDB(username, password);
	}
}
