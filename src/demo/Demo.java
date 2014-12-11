package demo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import player.User;
import player.UsersDAO;
import utilities.Utilities;
import cards.*;
import box.*;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Box box = context.getBean("fightBox", FightBox.class);
		for (int i = 0; i < box.getWildCards().size(); i++) {
			System.out.println(box.getWildCards().get(i).getCardInfo());
		}
		System.out.println();
		for (int i = 0; i < box.getDespicableCards().size(); i++) {
			System.out.println(box.getDespicableCards().get(i).getCardInfo());
		}
		UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
		  List<User> users = usersDao.getUsers();
		  
		  for(User u : users)
		  {
			  System.out.println(u);
		  }*/
		System.out.println(Utilities.getListOfAllUsers());
	}

}
