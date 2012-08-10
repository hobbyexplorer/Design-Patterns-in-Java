package org.knownspace.gamemaker.server.web.controllers;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.knownspace.gamemaker.server.entity.Game;
import org.knownspace.gamemaker.server.entity.GameKey;
import org.knownspace.gamemaker.server.entity.GameScore;
import org.knownspace.gamemaker.server.entity.GameUser;
import org.knownspace.gamemaker.server.entity.GameVersion;
import org.knownspace.gamemaker.server.entity.GameRating;
import org.knownspace.gamemaker.server.services.GameKeyService;
import org.knownspace.gamemaker.server.services.GameScoreService;
import org.knownspace.gamemaker.server.services.GameService;
import org.knownspace.gamemaker.server.services.GameUserService;
import org.knownspace.gamemaker.server.services.GameRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/site")
@SessionAttributes("users")
public class MainController
{

	@Autowired
	private GameUserService gameUserService;

	@Autowired
	private GameService gameService;

	@Autowired
	private GameScoreService gameScoreService;

	@Autowired
	private GameRatingService gameRatingService;

	@Autowired
	private GameKeyService gameKeyService;

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home()
	{
		return "home";
	}

	// @RequestMapping(value = "login", method = RequestMethod.GET)
	// public String login(Model uiModel, HttpServletRequest request) {
	//
	// String credIncorrect = request.getParameter("incorrectCredentials");
	// String credEmpty = request.getParameter("credentialsEmpty");
	// uiModel.addAttribute("incorrectCredentials", credIncorrect);
	// uiModel.addAttribute("credentialsEmpty", credEmpty);
	// return "login";
	// }

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String validate(Model uiModel, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("validate");
		String action = request.getParameter("submit");

		HttpSession session = request.getSession(true);
		String uname = request.getParameter("userName");
		String password = request.getParameter("password");
		if (password.isEmpty() || uname.isEmpty())
		{
			uiModel.addAttribute("credentialsEmpty", "true");
			session.setAttribute("userName", "");
			return "home";
		}

		if (gameUserService.checkUserExists(uname, password))
		{
			GameUser user = gameUserService.getUserByUserName(uname);
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userName", uname);
			session.setAttribute("user", user);
			System.out.println(session.getAttribute("user"));
			int gameId = 0;
			int version = 0;
			boolean gameMakerMode = true;

			int userId = (int) user.getUserId();
			createJNLPFile(userId, gameId, version, gameMakerMode, request, response, session);

			return "redirect:home";
		}
		else
		{
			return "redirect:home";
		}
	}

	@RequestMapping(value = "forgotpassword", method = RequestMethod.GET)
	public String forgotpassword(Model uiModel, HttpServletRequest request)
	{
		return "forgotpassword";
	}

	@RequestMapping(value = "getpassword", method = RequestMethod.POST)
	public String getpassword(Model uiModel, HttpServletRequest request, HttpSession session)
	{
		if (request.getParameter("userName") != null && request.getParameter("email") != null)
		{
			String userName = request.getParameter("userName");

			GameUser user = gameUserService.getUserByUserName(userName);

			if (user != null && user.getEmail().compareTo(request.getParameter("email")) == 0)
			{
				session.setAttribute("forgotPasswordUser", user);

				if (session.getAttribute("noPasswordUser") != null)
				{
					session.removeAttribute("noPasswordUser");
				}

				// call php page on haomin's server to send a mail with user
				// name and password
				return "redirect:http://140.182.173.16/mailpassword.php?userName=" + user.getUserName() + "&name=" + user.getName() + "&password=" + user.getPassword() + "&email=" + user.getEmail() + "&subject=Game Maker - Password Retrieval Request";
			}
			else
			{
				System.out.println("email did not match");
				System.out.println("User email: " + user.getEmail());
				System.out.println("Entered email: " + request.getParameter("email"));
				if (session.getAttribute("forgotPasswordUser") != null)
				{
					session.removeAttribute("forgotPasswordUser");
				}

				session.setAttribute("noPasswordUser", userName);
			}
		}

		return "redirect:forgotpassword";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model uiModel, HttpServletRequest request)
	{
		System.out.println("I am in logout");
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "home";
	}

	@RequestMapping(value = "score", method = RequestMethod.GET)
	public String score(Model uiModel, HttpServletRequest request, HttpSession session)
	{
		List<GameScore> allScores = gameScoreService.getAllScores();
		List<GameScore> topScores = gameScoreService.getTopScores();
		List<GameScore> myScores = new ArrayList<GameScore>();

		if (allScores != null)
		{
			uiModel.addAttribute("allScores", allScores);
			uiModel.addAttribute("topScores", topScores);
		}

		GameUser user = (GameUser) session.getAttribute("user");
		if (user != null)
		{
			long userId;
			userId = user.getUserId();
			System.out.println("build page recieved user id from sessions as : " + userId);
			uiModel.addAttribute("userId", userId);
			myScores = gameScoreService.getAllScoresForUser(userId);
			if (myScores != null)
			{
				uiModel.addAttribute("myScores", myScores);
			}

			return "score";
		}
		else
		{
			return "scorecommon";
		}

	}

	/**
	 * Will return the play game page with a "allGames" attributes that contain
	 * games records from the database
	 * 
	 * @param Model
	 *            uiModel
	 * @param HttpServletRequest
	 *            request
	 * @return String
	 */
	@RequestMapping(value = "playgame", method = RequestMethod.GET)
	public String playgame(Model uiModel, HttpServletRequest request, HttpSession session)
	{
		List<Game> allGames = gameService.getAllPublishedGames();

		if (allGames != null)
		{
			uiModel.addAttribute("allGames", allGames);
		}
		List<Game> topGames = gameService.getTopGames();
		if (topGames != null)
		{
			uiModel.addAttribute("topGames", topGames);
		}
		GameUser user = (GameUser) session.getAttribute("user");
		if (user != null)
		{
			long userId;
			userId = user.getUserId();
			System.out.println("play game page recieved user id from sessions as : " + userId);
			uiModel.addAttribute("userId", userId);
			List<Game> myGames = gameService.getAllGames(userId);
			if (myGames != null)
			{
				uiModel.addAttribute("myGames", myGames);
			}
			return "playgame";
		}
		else
		{
			return "playgamecommon";
		}
	}

	@RequestMapping(value = "download", method = RequestMethod.GET)
	public String download(Model uiModel, HttpServletRequest request, HttpSession session)
	{
		double userRating;
		GameUser user = (GameUser) session.getAttribute("user");
		if (user != null)
		{
			long userId;
			userId = user.getUserId();
			System.out.println("User ID received on Download page: " + userId);
			userRating = gameRatingService.getUserRating(userId, Long.parseLong(request.getParameter("gameId")));
			uiModel.addAttribute("userrating", userRating);
		}
		Game oneGame = new Game();
		oneGame = gameService.getGame(Long.parseLong(request.getParameter("gameId")));
		uiModel.addAttribute("gameforrating", oneGame);
		return "download";
	}

	@RequestMapping(value = "errorlogin", method = RequestMethod.GET)
	public String errorlogin(Model uiModel, HttpServletRequest request)
	{
		return "errorlogin";
	}

	@RequestMapping(value = "finalbuildgame", method = RequestMethod.GET)
	public String finalbuildgame(Model uiModel, HttpServletRequest request, HttpSession session)
	{

		long gameId = Long.parseLong(request.getParameter("gameId"));
		long userId = Long.parseLong(request.getParameter("userId"));
		String gameName = request.getParameter("gameName");
		int version = Integer.parseInt(request.getParameter("version"));
		uiModel.addAttribute("gameId", gameId);
		uiModel.addAttribute("gameName", gameName);
		uiModel.addAttribute("userId", userId);
		uiModel.addAttribute("version", version);
		return "finalbuildgame";
	}

	@RequestMapping(value = "buildgame", method = RequestMethod.GET)
	public String buildgame(Model uiModel, HttpServletRequest request, HttpSession session)
	{
		GameUser user = (GameUser) session.getAttribute("user");
		if (user != null)
		{
			long userId;
			userId = user.getUserId();
			System.out.println("build page recieved user id from sessions as : " + userId);
			uiModel.addAttribute("userId", userId);
			List<Game> allGames = gameService.getAllGames(userId);
			if (allGames != null)
			{

				if (session.getAttribute("allNewGames") == null)
				{
					uiModel.addAttribute("allGames", allGames);
				}
				else
				{
					List<Game> allNewGames = (List<Game>) session.getAttribute("allNewGames");
					uiModel.addAttribute("allGames", allNewGames);
				}
			}

			return "buildgame";
		}
		else
		{
			return "errorlogin";
		}
	}

	@RequestMapping(value = "retrieveExtraGame", method = RequestMethod.POST)
	public String retrieveExtraGame(Model uiModel, HttpServletRequest request, HttpSession session)
	{
		GameUser user = (GameUser) session.getAttribute("user");

		long userId;
		Game coopGame;
		String gameKey = request.getParameter("r_key");
		long gameId = gameKeyService.isGameKeyPresent(gameKey);
		List<Game> allGames;
		userId = user.getUserId();
		System.out.println("build page recieved user id from sessions as : " + userId);
		uiModel.addAttribute("userId", userId);
		if (session.getAttribute("allNewGames") == null)
		{
			allGames = gameService.getAllGames(userId);
		}
		else
		{
			allGames = (List<Game>) session.getAttribute("allNewGames");
		}

		if (gameId > 0)
		{
			coopGame = gameService.getGame(gameId);
			if (allGames == null)
				allGames = new ArrayList<Game>();
			allGames.add(coopGame);
			session.setAttribute("allNewGames", allGames);
			gameKeyService.delete(gameKey);
		}
		if (allGames != null)
		{
			uiModel.addAttribute("allGames", allGames);
		}
		return "buildgame";

	}

	@RequestMapping(value = "buildgameversion", method = RequestMethod.GET)
	public String buildgameversion(Model uiModel, HttpServletRequest request, HttpSession session)
	{
		session = request.getSession(true);
		if (session.getAttribute("gameId") == null || session.getAttribute("userId") == null)
		{
			session = request.getSession(true);
			long gameId = Long.parseLong(request.getParameter("gameId"));
			long userId = Long.parseLong(request.getParameter("userId"));
			String gameName = request.getParameter("gameName");
			Game game = gameService.getGame(gameId);
			session.setAttribute("gameName", game.getName());
			session.setAttribute("gameId", game.getGameId());
			System.out.println("user id is " + userId);
			System.out.println("game id is " + gameId);
			System.out.println(session.getAttribute("gameId"));

			List<GameVersion> allGameVersions = gameService.getGame(gameId).getVersions();
			if (allGameVersions != null)
			{
				uiModel.addAttribute("allGameVersions", allGameVersions);
				System.out.println("entered game version list");
			}
			uiModel.addAttribute("gameId", gameId);
			uiModel.addAttribute("gameName", gameName);
			uiModel.addAttribute("userId", userId);

		}
		// else if
		// (!session.getAttribute("gameId").toString().equals(request.getParameter("gameId").toString())||!session.getAttribute("userId").toString().equals(request.getParameter("userId").toString()))
		else if (!(request.getParameter("gameId") == null) || !(request.getParameter("userId") == null))
		{
			session = request.getSession(true);
			long gameId = Long.parseLong(request.getParameter("gameId"));
			long userId = Long.parseLong(request.getParameter("userId"));
			String gameName = request.getParameter("gameName");
			Game game = gameService.getGame(gameId);
			session.setAttribute("gameName", game.getName());
			session.setAttribute("gameId", game.getGameId());
			System.out.println("user id is " + userId);
			System.out.println("game id is " + gameId);
			System.out.println(session.getAttribute("gameId"));

			List<GameVersion> allGameVersions = gameService.getGame(gameId).getVersions();
			if (allGameVersions != null)
			{
				uiModel.addAttribute("allGameVersions", allGameVersions);
				System.out.println("entered game version list");
			}
			uiModel.addAttribute("gameId", gameId);
			uiModel.addAttribute("gameName", gameName);
			uiModel.addAttribute("userId", userId);
		}
		else
		{

			long gameId = (Long) (session.getAttribute("gameId"));
			long userId = (Long) (session.getAttribute("userId"));
			String gameName = (String) (session.getAttribute("gameName"));
			Game game = gameService.getGame(gameId);
			session.setAttribute("gameId", game.getGameId());
			System.out.println("user id is " + userId);
			System.out.println("game id is " + gameId);
			System.out.println(session.getAttribute("gameId"));
			System.out.println(session.getAttribute("userId"));

			List<GameVersion> allGameVersions = gameService.getGame(gameId).getVersions();
			if (allGameVersions != null)
			{
				uiModel.addAttribute("allGameVersions", allGameVersions);
				System.out.println("entered game version list");
			}
			uiModel.addAttribute("gameId", gameId);
			uiModel.addAttribute("gameName", gameName);
			uiModel.addAttribute("userId", userId);
		}
		return "buildgameversion";
	}

	@RequestMapping(value = "keyadded", method = RequestMethod.GET)
	public String key(Model uiModel, HttpServletRequest request)
	{
		return "keyadded";
	}

	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String contact()
	{
		return "contact";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register()
	{
		return "register";
	}

	@RequestMapping(value = "help", method = RequestMethod.GET)
	public String help()
	{
		return "help";
	}

	@RequestMapping(value = "addGameKey", method = RequestMethod.POST)
	public String addGameKey(Model uiModel, HttpServletRequest request, HttpSession session)
	{
		session = request.getSession(true);
		long gameId = (Long) (session.getAttribute("gameId"));

		String gameKey = request.getParameter("r_key");
		Long checkGameKey = gameKeyService.isGameKeyPresent(gameKey);
		GameKey gamekey = new GameKey();
		if (checkGameKey == 0)
		{
			gamekey.setGameKey(gameKey);

			session.setAttribute("gamekey", gamekey.getGameKey());
			gamekey.setGameId(gameId);
			System.out.println("the gamekey in session is:" + session.getAttribute("gamekey"));
			System.out.println("the gameIs for the game key is:" + gamekey.getGameId());
			System.out.println("the gamekey is" + gamekey.getGameKey());
			boolean result = gameKeyService.save(gamekey);
			System.out.println(result);
		}
		else
		{
			System.out.println("same key, saving failed");
		}

		return "keyadded";
	}

	@RequestMapping(value = "adduser", method = RequestMethod.POST)
	public String adduser(Model uiModel, HttpServletRequest request)
	{
		GameUser user = new GameUser();

		user.setName(request.getParameter("name"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		try
		{

			int ageValue = Integer.parseInt(request.getParameter("age"));
			user.setAge(ageValue);

		}
		catch (NumberFormatException ex)
		{
			user.setAge(0);
		}

		if (request.getParameter("sexField") == "Male")
		{
			System.out.println("Sex = Male");
			user.setSex('M');
		}
		else if (request.getParameter("sexField") == "Female")
		{
			System.out.println("Sex = Female");
			user.setSex('F');
		}

		gameUserService.save(user);

		return "redirect:home";
	}

	// @RequestMapping(value = "login", method = RequestMethod.POST)
	// public String login(Model uiModel, HttpServletRequest request)
	// {
	// Boolean loginStatus =
	// gameUserService.verifyUserPassword(request.getParameter("userName"),
	// request.getParameter("password"));
	//
	// if (loginStatus)
	// {
	// GameUser user =
	// gameUserService.getUserByUserName(request.getParameter("userName"));
	// if (user != null)
	// {
	// List<GameUser> users = new ArrayList<GameUser>();
	// users.add(user);
	// uiModel.addAttribute("users", users);
	// }
	// }
	//
	// return "redirect:buildonlogin";
	// }
	//
	// @RequestMapping(value = "logout", method = RequestMethod.GET)
	// public String logout(Model uiModel, HttpServletRequest request)
	// {
	// uiModel.addAttribute("user", null);
	//
	// return "redirect:home";
	// }

	// @RequestMapping(value = "buildonlogin", method = RequestMethod.GET)
	// public String buildGamePrep(Model uiModel, HttpServletRequest request,
	// HttpServletResponse response, HttpSession session)
	// {
	// GameUser user = (GameUser)session.getAttribute("user");
	// System.out.println((int) user.getUserId());
	// int userId = (int) user.getUserId();
	// int gameId = 0;
	// int version = 0;
	// boolean gameMakerMode = true;
	//
	// System.out.println("buildonlogin");
	// createJNLPFile(userId, gameId, version, gameMakerMode, request, response,
	// session);
	//
	// return "redirect:home";
	// }

	@RequestMapping(value = "buildgameversionlist", method = RequestMethod.POST)
	public String buildgameversionlist(Model uiModel, HttpServletRequest request)
	{
		long gameId = Long.parseLong(request.getParameter("gameId"));
		String gameName = request.getParameter("gameName");
		long userId = Long.parseLong(request.getParameter("userId"));
		System.out.println("Return text: " + "buildgameversion?gameId=" + gameId + "&gameName=" + gameName + "&userId=" + userId);
		return "redirect:buildgameversion?gameId=" + gameId + "&gameName=" + gameName + "&userId=" + userId;
	}

	@RequestMapping(value = "buildonbuildgameversion", method = RequestMethod.POST)
	public String buildonbuildgameversion(Model uiModel, HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		int userId = Integer.parseInt(request.getParameter("userId"));
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		int version = Integer.parseInt(request.getParameter("version"));
		String gameName = request.getParameter("gameName");
		boolean gameMakerMode = true;

		createJNLPFile(userId, gameId, version, gameMakerMode, request, response, session);
		System.out.println("Return text in buildonbuild: " + "download?user=" + userId + "&gameId=" + gameId + "&version=" + version);
		return "redirect:finalbuildgame?user=" + userId + "&gameId=" + gameId + "&version=" + version + "&userId=" + userId + "&gameName=" + gameName;
	}

	@RequestMapping(value = "buildonplaygame", method = RequestMethod.POST)
	public String buildonplaygame(Model uiModel, HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		int userId = Integer.parseInt(request.getParameter("userId"));
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		int version = Integer.parseInt(request.getParameter("version"));
		boolean gameMakerMode = false;

		createJNLPFile(userId, gameId, version, gameMakerMode, request, response, session);
		System.out.println("Return text for buildon play game: " + "download?user=" + userId + "&gameId=" + gameId + "&version=" + version);
		return "redirect:download?user=" + userId + "&gameId=" + gameId + "&version=" + version;
	}

	@RequestMapping(value = "saverating", method = RequestMethod.POST)
	public String saverating(Model uiModel, HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		int userId = Integer.parseInt(request.getParameter("userId"));
		int gameId = Integer.parseInt(request.getParameter("gameId"));
		int version = Integer.parseInt(request.getParameter("version"));
		int ratingInput = Integer.parseInt(request.getParameter("ratingInput"));
		boolean gameMakerMode = false;

		GameRating gameRating = new GameRating();

		gameRating.setUserId(userId);
		gameRating.setGameId(gameId);
		gameRating.setGameRating(ratingInput);
		gameRating.setTimeStamp(new Date());

		gameRatingService.saveGameRating(gameRating);
		createJNLPFile(userId, gameId, version, gameMakerMode, request, response, session);
		System.out.println("Return text for save rating: " + "download?user=" + userId + "&gameId=" + gameId + "&version=" + version);
		System.out.println("Rating input: " + ratingInput);
		System.out.println("Redirecting to download page...");
		return "redirect:download?user=" + userId + "&gameId=" + gameId + "&version=" + version;
	}

	@SuppressWarnings("deprecation")
	public void createJNLPFile(int userId, int gameId, int version, boolean gameMakerMode, HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
		File jnlpFile;

		boolean frontSlash = false;

		String parentDirectoryPath = request.getRealPath("");
		if (parentDirectoryPath.indexOf("/") != -1)
		{
			frontSlash = true;
		}

		if (gameMakerMode)
		{
			if (frontSlash)
			{
				jnlpFile = new File(parentDirectoryPath + "/build_jnlp/team3-a6-user-" + userId + "-game-" + gameId + "-version-" + version + ".jnlp");
			}
			else
			{
				jnlpFile = new File(parentDirectoryPath + "\\build_jnlp\\team3-a6-user-" + userId + "-game-" + gameId + "-version-" + version + ".jnlp");
			}
		}
		else
		{
			if (frontSlash)
			{
				jnlpFile = new File(parentDirectoryPath + "/play_jnlp/team3-a6-user-" + userId + "-game-" + gameId + "-version-" + version + ".jnlp");
			}
			else
			{
				jnlpFile = new File(parentDirectoryPath + "\\play_jnlp\\team3-a6-user-" + userId + "-game-" + gameId + "-version-" + version + ".jnlp");
			}
		}

		try
		{

			if (!jnlpFile.createNewFile())
			{
				return;
			}

		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PrintWriter out = null;
		try
		{
			out = new PrintWriter(jnlpFile);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		if (gameMakerMode)
		{
			out.println("<jnlp spec=\"1.0+\" codebase=\"http://fluency.knownspace.org/webstart/student-projects/fall2011/a6/team3\" >");
		}
		else
		{
			out.println("<jnlp spec=\"1.0+\" codebase=\"http://fluency.knownspace.org/webstart/student-projects/fall2011/a6/team3\" >");
		}

		out.println("<information>");
		if (gameMakerMode)
		{
			out.println("<title>A6, Team 03, User " + userId + "</title>");
		}
		else
		{
			out.println("<title>A6, Team 03, User " + userId + ", Game " + gameId + ", Version " + version + "</title>");
		}

		out.println("<vendor>P532</vendor>");
		out.println("<homepage href=\"http://fluency.knownspace.org/confluence\" />");
		if (gameMakerMode)
		{
			// build game mode
			out.println("<description>Build Game JNLP for user with userId " + userId + "</description> ");
			out.println("<description kind=\"short\">Build Game JNLP</description>");
		}
		else
		{
			// play game mode
			out.println("<description>Play Game JNLP for user with user ID " + userId + " and game with game ID " + gameId + "</description> ");
			out.println("<description kind=\"short\">Play Game JNLP</description>");
		}

		out.println("<icon href=\"kslogo.gif\" />");
		out.println("</information>");
		out.println("<offline-allowed />");
		out.println("<security>");
		out.println("<all-permissions/>");
		out.println("</security>");
		out.println("<resources>");
		out.println("<j2se version=\"1.6+\"/>");
		out.println("<jar href=\"team3-a6.jar\" />");
		out.println("</resources>");
		out.println("<application-desc main-class=\"gamemaker.GameMaker\">");
		out.println("<argument>" + userId + "</argument>");
		out.println("<argument>" + gameId + "</argument>");
		out.println("<argument>" + version + "</argument>");
		out.println("<argument>" + gameMakerMode + "</argument>");
		out.println("</application-desc>");
		out.println("</jnlp>");
		out.flush();
		out.close();
	}
}
