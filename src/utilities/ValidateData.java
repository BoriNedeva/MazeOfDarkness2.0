package utilities;

public class ValidateData {
	
	public static boolean validateUsername(String username)
	{
		return username.matches("^[a-zA-Z]([._-]*[a-zA-Z0-9]+){3,20}$");
	}
	
	public static boolean validatePassword(String password)
	{
		return password.matches("((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,32})");
	}
	
	public static boolean validateEmail(String email)
	{
		return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}).{,255}$");
	}
	
	public static boolean validateConfirmation(String password, String confirmedPassword)
	{
		return password.equals(confirmedPassword);
	}
	
	public static boolean validateNotEmpty(String input)
	{
		return input != null && !input.equals("");
	}
}
