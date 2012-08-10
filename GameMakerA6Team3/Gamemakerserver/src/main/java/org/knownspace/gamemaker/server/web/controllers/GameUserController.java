package org.knownspace.gamemaker.server.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.knownspace.gamemaker.server.entity.GameUser;
import org.knownspace.gamemaker.server.services.GameUserService;

@Controller 
@RequestMapping("/gameuser")
public class GameUserController {

	// Not thread safe, but ok for this simple example
		private static int counter = 1;
		
		@Autowired
		private GameUserService gameUserService;
		
		
	    @RequestMapping(value = "index", method = RequestMethod.GET)
	    public String index() {
	    	return "index";
	    }

	    @RequestMapping(value = "bootstrap", method = RequestMethod.GET)
	    public String bootstrap() {
			GameUser u1 = new GameUser();
			u1.setName("user" + counter);
			u1.setUserName("u" + counter);
			u1.setPassword("pass@123");
			gameUserService.save(u1);
			counter++;
			
			GameUser u2 = new GameUser();
			u2.setName("user" + counter);
			u2.setUserName("u" + counter);
			u2.setPassword("pass@123");
			gameUserService.save(u2);
			counter++;

			//return "redirect:all";
			return "index2";
	    }

	    @RequestMapping(value = "all", method = RequestMethod.GET)
	    public String retriveAllSamples(Model uiModel, HttpServletRequest request) {
	    	List<GameUser> users = gameUserService.getAllUsers();
	    	uiModel.addAttribute("users", users);
			return "datauser";
	    }

	    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
	    public String retriveById(@PathVariable("userId") Long userId, Model uiModel, HttpServletRequest request) {
	    	GameUser user = gameUserService.getUserById(userId);
	    	List<GameUser> users = new ArrayList<GameUser>();
	    	if (user != null) {
	    		users.add(user);
	    	}
	    	uiModel.addAttribute("users", users);
			return "datauser";
	    }

	    @RequestMapping(value = "{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	    @ResponseBody
	    public Object get(@PathVariable("userId") Long userId) {
	    	GameUser user = gameUserService.getUserById(userId);
	        if (user == null) {
	            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	        }
	        return user.toJson();
	    }
	    
	    @RequestMapping(headers = "Accept=application/json")
	    @ResponseBody
	    public String getListJson(HttpServletRequest request) {
	    	List<GameUser> users = gameUserService.getAllUsers();
	    	return gameUserService.toJson(users);
	    }


		

}
