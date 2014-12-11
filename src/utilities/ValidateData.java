package utilities;

import java.util.List;
import player.User;

public class ValidateData {
	
	public static boolean checkUsernameInDB(String username, List<User> users)
	{
		boolean isRegistered = false;
		for(User user : users)
		  {
			 if(user.getUsername().equals(username)){
				 isRegistered = true;
				 break;
			 }
		  }
		
		return isRegistered;
	}
	
	public static boolean checkPasswordInDB(String password, String username, List<User> users)
	{
		boolean isRightPassword = false;
		for(User user : users)
		  {
			 if(user.getUsername().equals(username) && user.getPassword().equals(password)){
				 isRightPassword = true;
				 break;
			 }
		  }
		
		return isRightPassword;
	}
	
	public static boolean validateUsername(String username)
	{
		return false;
	}
	
	public static boolean validatePassword(String password)
	{
		return false;
	}
	
	public static boolean validateEmail(String email)
	{
		return false;
	}
	
	public static boolean validateConfirmation(String confirmedPassword)
	{
		return false;
	}
	
	public static boolean validateNotEmpty(String input)
	{
		return input != null && !input.equals("");
	}
}
