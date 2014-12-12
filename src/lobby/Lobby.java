package lobby;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class Lobby {
	private HashMap<String, HttpSession> onlineUsers;
	
	public HashMap<String, HttpSession> getOnlineUsers() {
		return onlineUsers;
	}


	public Lobby() {
		onlineUsers = new HashMap<String, HttpSession>();
		
	}

	public synchronized void addUser(String user, HttpSession session) {
		if(!onlineUsers.containsKey(user)){
			onlineUsers.put(user, session);
		}
	}

	public synchronized void removeUser(String user, HttpSession session) {
		if(!onlineUsers.containsKey(user)){
			onlineUsers.remove(user);
		}
	}

	
	public synchronized HttpSession getSession(String user){
		
			return getOnlineUsers().get(user);
		
	}
	
}
