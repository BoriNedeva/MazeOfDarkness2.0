package player;

public class User {
	private String username;
	private String password;
	private String email;
	private Statistics statistics;
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", email=" + email + ", statistics=" + statistics + "]";
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Statistics getStatistics() {
		return statistics;
	}
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
}
