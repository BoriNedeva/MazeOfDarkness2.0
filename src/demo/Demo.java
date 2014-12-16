package demo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import player.FightPlayer;
import player.IPlayer;
import player.Player;
import player.Statistics;
import player.User;
import player.UsersDAO;
import utilities.ValidateData;
import cards.*;
import box.*;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		Box box = context.getBean("fightBox", FightBox.class);
		System.out.println(box.getWildCards().size());
		for (int i = 0; i < box.getWildCards().size(); i++) {
		//	System.out.println(box.getWildCards().get(i).getCardInfo());
		}
		System.out.println();
		for (int i = 0; i < box.getDespicableCards().size(); i++) {
		//	System.out.println(box.getDespicableCards().get(i).getCardInfo());
		}
		/*UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
		  List<User> users = usersDao.getUsers();
		  
		  for(User u : users)
		  {
			  System.out.println(u);
		  }*/
		//System.out.println(Utilities.getListOfAllUsers());
//		User u = new User();
//		Statistics s = new Statistics();
//		s.setHighScore(300);
//		s.setLevel(4);
//		s.setWins(11);
//		u.setStatistics(s);
//		Player p = new FightPlayer("bori", u);
//		p.setScore(400);
//		p.setHasWon(true);
//		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		UsersDAO usersDao = context.getBean("usersDAO", UsersDAO.class);
//		//usersDao.updateStatistics(p);
		
		//usersDao.registerNewUser("cobrata1", "Admin5", "cobrata@abv.bg");
		
		
//		System.out.println(ValidateData.validateUsername("^&*$&$#$"));
//		System.out.println(ValidateData.validateUsername(".bori"));
//		System.out.println(ValidateData.validateUsername("_bori"));
//		System.out.println(ValidateData.validateUsername(".bori"));
//		System.out.println(ValidateData.validateUsername("bori"));
//		System.out.println(ValidateData.validateUsername(".bori"));
//		System.out.println();
//		System.out.println(ValidateData.validateUsername("bori."));
//		System.out.println(ValidateData.validateUsername("bori_"));
//		System.out.println(ValidateData.validateUsername("bori-"));
//		System.out.println();
//		System.out.println(ValidateData.validateUsername("5bori"));
//		System.out.println(ValidateData.validateUsername("bori5"));
//		
//		System.out.println(ValidateData.validateUsername("lala.bori"));
//		System.out.println(ValidateData.validateUsername("lala5bori"));
//		System.out.println(ValidateData.validateUsername("lala_bori"));
//		System.out.println(ValidateData.validateUsername("lala-bori"));
//		System.out.println();
//		System.out.println(ValidateData.validateUsername("lala..bori"));
//		System.out.println(ValidateData.validateUsername("lala56bori_5"));
		
		//System.out.println(usersDao.checkEmailInDB("bori@gmail.com"));
		//System.out.println(usersDao.checkEmailInDB("bori1@gmail.com"));
		
//		System.out.println(ValidateData.validateEmail("bori@gmail.com"));
//		System.out.println(ValidateData.validateEmail("bori@.hdkajhkjhdks"));
//		System.out.println(ValidateData.validateEmail("bor"));
//		System.out.println(ValidateData.validateEmail("bori.hdkajhkjhdks"));
//		System.out.println(ValidateData.validateEmail("555565646d5s4"));
		
		System.out.println(usersDao.getTopUsers().toString());
		
	}

}
