package gamemaker;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import log4j.GameMakerLogger;
import model.GameBoardModel;
import model.TestModelAsteroids;
import model.TestModelSpaceInvaders;
import model.testModelFrogger;
import controller.GameBoardController;
import controller.GameCreatePanelController;
import controller.LoadGame;
import controller.StartPausePanelController;

/**
 * Class Name: GameMaker Class Responsibilities: the main class containing the
 * game contents Class collaborators: GameMaker.java
 */
public class GameMaker
{

	/*
	 * // The Constant gameBoard. private GameBoard gameBoard = new GameBoard();
	 */
	private static GameBoardController gameBoardController;

	private JPanel gameBoard;

	// The Constant tab.
	private JTabbedPane tab = new JTabbedPane();

	// The Constant gameMakerHelp.
	private GameMakerHelp gameMakerHelp = new GameMakerHelp();

	private GameCreatePanelController gameCreatePanelController;

	/*
	 * // The Constant gameCreatePanel. public static final GameCreatePanel
	 * gameCreatePanel = new GameCreatePanel();
	 */
	// The game maker.
	private JFrame gameMaker;

	// The Constant timerEvent.
	// public static final TimerEvent timerEvent = new
	// TimerEvent(GameBoardController.getInstance().getGameBoard());
	// public static final TimerEvent timerEvent = new TimerEvent(null);

	// The Constant keyEvent.
	// public static final KeyEvent keyEvent = new
	// KeyEvent(GameBoardController.getInstance().getGameBoard(), timerEvent);
	// public static final KeyEvent keyEvent = new KeyEvent(null, timerEvent);

	// The Constant victoryEvent.
	// public static final VictoryEvent victoryEvent = new VictoryEvent();

	// The Constant spriteList.
	// public static SpriteObjectList spriteList = new SpriteObjectList();

	// The Constant gameObject.
	// public static final GameObject gameObject = new GameObject();

	// The timercount.
	public static int timercount = 0;

	// The game area.
	public static JPanel gameArea = new JPanel();

	// The timer panel.
	public JPanel timerPanel = new JPanel();

	// The game time.
	public static JTextField gameTime = new JTextField();

	public static JLabel gameScore = new JLabel();
	public static final GameMakerLogger logger = new GameMakerLogger("log4j/gamemaker_logger.properties");

	private GameBoardModel gameBoardModel;

	private boolean gameMakerMode = true;

	private GameMakerParams gameParams;

	 private testModelFrogger testModel;
	//private TestModelAsteroids testModel;
	//private TestModelSpaceInvaders testModel;
	private StartPausePanel startPausePanel;

	// Constructor GameMaker: Instantiates a new game maker.
	public GameMaker(int userID, int gameID, int version, boolean gameMakerMode)
	{
		GameMakerParams.getInstance().setUserID(userID);
		GameMakerParams.getInstance().setGameID(gameID);
		GameMakerParams.getInstance().setVersion(version);
		GameMakerParams.getInstance().setGameMakerMode(gameMakerMode);

		gameBoardController = GameBoardController.getInstance();
		gameCreatePanelController = GameCreatePanelController.getInstance();

		gameBoardModel = new GameBoardModel();

		//testModel = new TestModelAsteroids();

		//testModel = new TestModelSpaceInvaders();

		 testModel = new testModelFrogger();

	    //gameBoardModel = testModel.getGameBoardModel();

		gameBoardController.setGameBoardModel(gameBoardModel);
		gameCreatePanelController.setGameBoardModel(gameBoardModel);

		this.gameMaker = new JFrame("GameMaker");
		this.gameMaker.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

		if (GameMakerParams.getInstance().isGameMakerMode())
		{

			this.gameMaker.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
			this.gameMaker.setLayout(new GridLayout(0, 2));
			this.gameMaker.add(tab, BorderLayout.CENTER);
			tab.add("Make Game", gameCreatePanelController.getGameCreatePanel().getGameCreatePanel());
			tab.add("Help", gameMakerHelp.getHelpPanel());
		}
		else
		{
			this.gameMaker.setSize(Constants.GAMEBOARD_WIDTH, Constants.FRAME_HEIGHT);
			this.gameMaker.setLayout(new GridLayout(0, 1));
		}

		gameArea.setSize(Constants.GAMEBOARD_WIDTH, Constants.GAMEBOARD_HEIGHT);
		gameArea.setLayout(null);

		this.gameMaker.add(gameArea);
		gameTime.setFont(new Font("Arial", Font.BOLD, 20));
		gameTime.setText("00:" + "00:" + "00");

		gameScore.setFont(new Font("Arial", Font.BOLD, 20));
		gameScore.setText("Score :" + "0");

		gameBoardController.setGameTime(gameTime);
		gameBoardController.setGameScore(gameScore);

		this.timerPanel.setSize(new Dimension(Constants.GAMEBOARD_WIDTH, 40));
		this.timerPanel.add(gameTime);
		this.timerPanel.add(gameScore);
		this.timerPanel.setBounds(Constants.TIME_PANEL_XPADDING, Constants.TIME_PANEL_YPADDING, Constants.TIME_PANEL_WIDTH, Constants.TIME_PANEL_HEIGHT);

		this.startPausePanel = StartPausePanelController.getInstance().getStartPausePanel();
		this.startPausePanel.getStartPausePanel().setBounds(Constants.STARTPAUSEPANEL_GAMEAREA_XPADDING, Constants.STARTPAUSEPANEL_GAMEAREA_YPADDING, Constants.STARTPAUSEPANEL_GAMEAREA_WIDTH, Constants.STARTPAUSEPANEL_GAMEAREA_HEIGHT);
		// gameArea.add(this.startPausePanel.getStartPausePanel());
		disableOnStart();

		this.gameBoard = gameBoardController.getGameBoard().getGameBoard();
		this.gameBoard.setBounds(Constants.GAMEBOARD_XPADDING, Constants.GAMEBOARD_YPADDING, Constants.GAMEBOARD_WIDTH, Constants.GAMEBOARD_HEIGHT);

		gameArea.setLayout(new GridBagLayout());
		GridBagConstraints constrains = new GridBagConstraints();
		constrains.weighty = 0.01;
		constrains.weightx = 0.5;
		constrains.fill = GridBagConstraints.BOTH;
		constrains.anchor = GridBagConstraints.FIRST_LINE_START;
		constrains.weightx = 1;
		constrains.gridx = 0;
		constrains.gridy = 0;
		constrains.gridheight = 1;
		constrains.gridwidth = 1;
		gameArea.add(this.timerPanel, constrains);

		constrains.weighty = 0.01;
		constrains.weightx = 0.5;
		constrains.gridwidth = GridBagConstraints.HORIZONTAL;
		constrains.anchor = GridBagConstraints.FIRST_LINE_END;
		constrains.gridy = 0;
		constrains.gridx = 1;
		constrains.gridheight = 1;
		constrains.gridwidth = 1;
		gameArea.add(this.startPausePanel.getStartPausePanel(), constrains);

		constrains.weighty = 0.99;
		constrains.gridy = 1;
		constrains.gridx = 0;
		constrains.fill = GridBagConstraints.BOTH;
		constrains.gridheight = 1;
		constrains.gridwidth = 2;
		gameArea.add(this.gameBoard, constrains);

		this.gameMaker.setVisible(true);
		this.gameMaker.setDefaultCloseOperation(3);
		if(!GameMakerParams.getInstance().isGameMakerMode())
		{
			try
			{
			new LoadGame().loadGame("",gameID,userID,version);
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Game could not be loaded. please contact the dev team");
			}
		}
		else if (GameMakerParams.getInstance().isGameMakerMode())
		{
			if(version >0)
			{
				try
				{
				new LoadGame().loadGame("",gameID,userID,version);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Game could not be loaded. please contact the dev team");
				}
			}
		}
		GameMaker.logger.logInfo(this.getClass().getName() + " - " + "Initialized the game maker.");

	}

	/* Method main: The main method. */
	public static void main(String[] args)
	{
		int userID;
		int gameID;
		int version;
		boolean gameMakerMode;
		try
		{
			userID = Integer.parseInt(args[0]);
		}
		catch (Exception e)
		{
			userID = 99;
		}
		try
		{
			gameID = Integer.parseInt(args[1]);
		}
		catch (Exception e)
		{
			gameID = 0;
		}
		try
		{
			version = Integer.parseInt(args[2]);
		}
		catch (Exception e)
		{
			version = 0;
		}
		try
		{
			if (args[3].trim().equals("true"))
			{
				gameMakerMode = true;
			}
			else
			{
				gameMakerMode = false;
			}
		}
		catch (Exception e)
		{
			gameMakerMode = true;
			GameMaker.logger.logError(Boolean.toString(gameMakerMode));
		}
		System.out.println("userID: " + userID + " gameID: " + gameID + " version: " + version + " gameMakerMode: " + gameMakerMode);
		new GameMaker(userID, gameID, version, gameMakerMode);
	}

	/* Method getGameTime: Gets the game time. */
	public JTextField getGameTime()
	{
		return gameTime;
	}

	/* Method setGameTime : Sets the game time. */
	public static void setGameTime(JTextField gameTime)
	{
		gameTime.setText(gameTime.getText());
	}

	public GameBoardModel getGameBoardModel()
	{
		return gameBoardModel;
	}

	public void setGameBoardModel(GameBoardModel gameBoardModel)
	{
		this.gameBoardModel = gameBoardModel;
	}

	public static GameBoardController getGameBoardController()
	{
		return gameBoardController;
	}

	public void setGameBoardController(GameBoardController gameBoardController)
	{
		this.gameBoardController = gameBoardController;
	}

	public StartPausePanel getStartPausePanel()
	{
		return startPausePanel;
	}

	public void setStartPausePanel(StartPausePanel startPausePanel)
	{
		this.startPausePanel = startPausePanel;
	}

	public void disableOnStart()
	{
		this.startPausePanel.getPause().setEnabled(false);
		this.startPausePanel.getRebuild().setEnabled(false);
	}

}