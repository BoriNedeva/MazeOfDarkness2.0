package lobby;import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class Lobby {
	private HashMap<String, HttpSession> onlineUsers;
	private static Lobby lobby = null;
	public HashMap<String, HttpSession> getOnlineUsers() {
		return onlineUsers;
		
	}


	public Lobby() {
		onlineUsers = new HashMap<String, HttpSession>();
		
	}

	public static Lobby getLobby(){
		if(lobby ==  null)
			lobby = new Lobby();
		return lobby;
	}
	
	public synchronized void addUser(String user, HttpSession session) {
		if(!onlineUsers.containsKey(user)){
			onlineUsers.put(user, session);
		}
	}

	public synchronized void removeUser(String user, HttpSession session) {
		if(onlineUsers.containsKey(user)){
			onlineUsers.remove(user, session);
		}
	}

	
	public synchronized HttpSession getSession(String user){
		
			return getOnlineUsers().get(user);
		
	}
	
	
	
	
}
