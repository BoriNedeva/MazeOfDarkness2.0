package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LobbyController {
	@RequestMapping(value="/Lobby", method = RequestMethod.GET)
	public String handleLobby(){
		return "Lobby";
	}
}
