package controller;

import gamemaker.Constants;
import gamemaker.GameBoard;
import gamemaker.GameMaker;
import gamemaker.GameBoardRightClick;
import gamemaker.GameMakerParams;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import databaseconnector.DatabaseConnector;
import model.GameBoardModel;
import model.GameMacroCommand;
import model.GameObject;
import model.GameObjectCommand;
import model.Sprite;
import model.SpriteCommand;
import model.event.CollisionEvent;
import model.event.CollisionEventWithException;
import model.event.CountEvent;
import model.event.Event;
import model.event.GenerateAtPosition;
import model.event.GenerateRandom;
import model.event.KeyPressedEvent;
import model.event.TimerEvent;

/**
 * Class Name: GameBoardController. Class responsibility: Contains the actions
 * for the background Selection Class Collaborators:
 */
public class GameBoardController implements KeyListener, MouseMotionListener, MouseListener, ActionListener
{

	private static GameBoardController instance;

	private GameBoard gameBoard;
	private GameBoardModel gameBoardModel;
	private Timer timer;
	private int scoreCount = 0;

	private int timerCount = 0;
	private int timerInterval = 10;
	private int secondsElapsed;
	private boolean stopGameFlag;
	private String gameMessage;
	private GameMacroCommand gameMacroCommand;
	private String Cursor = null;
	private int range = 5;

	private int begin_x;
	private int begin_y;

	private int[] minsAndSecs = { 0, 0 };

	private JTextField gameTime;
	private JLabel gameScore;

	private GameBoardController()
	{
		gameBoard = new GameBoard();
		// getGameBoard().getGameBoard().addKeyListener(this);
		getGameBoard().getGameBoard().addMouseMotionListener(this);
		getGameBoard().getGameBoard().addMouseListener(this);
		timer = new Timer(timerInterval, this);
		// setGameVariables();

		setGameMacroCommand(new GameMacroCommand());

		timerCount = 0;
		secondsElapsed = 0;
		stopGameFlag = false;
		gameMessage = "";
		minsAndSecs[0] = 0;
		minsAndSecs[1] = 0;

	}

	public void setGameVariables()
	{
		timerCount = 0;
		secondsElapsed = 0;
		stopGameFlag = false;
		gameMessage = "";
		minsAndSecs[0] = 0;
		minsAndSecs[1] = 0;
		gameTime.setText("00:" + "00:" + "00");

	}

	public void setStopGameFlag(boolean stopGameFlag)
	{
		this.stopGameFlag = stopGameFlag;
	}

	public boolean isStopGameFlag()
	{
		return stopGameFlag;
	}

	public void setGameMessage(String gameMessage)
	{
		this.gameMessage = gameMessage;
	}

	public String getGameMessage()
	{
		return gameMessage;
	}

	public int getSecondsElapsed()
	{
		return secondsElapsed;
	}

	public void setSecondsElapsed(int secondsElapsed)
	{
		this.secondsElapsed = secondsElapsed;
	}

	public void resetTimer()
	{
		secondsElapsed = 0;
		timerCount = 0;
	}

	public boolean updateTime()
	{
		timerCount = timerCount + timerInterval;
		if (timerCount % 1000 == 0)
		{
			if (minsAndSecs[1] % 1 == 0 && minsAndSecs[1] != 0)
			{
				// scoreCount += Integer.parseInt(GameMaker.getScoreText());
			}
			if (minsAndSecs[1] == 60)
			{

				minsAndSecs[1] = 0;
				minsAndSecs[0] += 1;
			}
			else
			{
				minsAndSecs[1] += 1;
			}

			secondsElapsed = (minsAndSecs[0] * 60 + minsAndSecs[1]);

			String minutes = Integer.toString(minsAndSecs[0]);
			String seconds = Integer.toString(minsAndSecs[1]);
			if (minsAndSecs[1] < 10)
				seconds = "0" + seconds;
			if (minsAndSecs[0] < 10)
				minutes = "0" + minutes;

			gameScore.setText("Score :" + Integer.toString(scoreCount));
			gameTime.setText("00:" + minutes + ":" + seconds);
			return true;
		}

		return false;

	}

	public static GameBoardController getInstance()
	{
		if (instance != null)
		{
			return instance;
		}
		instance = new GameBoardController();
		return instance;
	}

	public void setImageToBackground(String imageName)
	{
		gameBoard.setImageToBackground(imageName);
	}

	public GameBoard getGameBoard()
	{
		return gameBoard;
	}

	public void setGameBoardModel(GameBoardModel gameBoardModel)
	{
		this.gameBoardModel = gameBoardModel;
		this.gameBoard.setSpriteList(gameBoardModel.getSpriteList());
	}

	public GameBoardModel getGameBoardModel()
	{
		return gameBoardModel;
	}

	Sprite sprite1 = null;
	// New object of sprite selected.
	GameObject objectSelected = new GameObject(begin_x, begin_x, begin_x, begin_x, begin_x, begin_x, begin_x);
	ArrayList<GameObject> objectSelecteds = new ArrayList<GameObject>();
	// The sprites elected.
	boolean isObjectSelected = false;

	public void setSelectObjectLocation()
	{
		begin_x = objectSelected.getX();
		begin_y = objectSelected.getY();
	}

	public void setCursor(int x2, int y2)
	{
		int x = 0, y = 0, w = 0, h = 0;

		if (objectSelected != null)
		{
			int x1 = objectSelected.getX();
			int y1 = objectSelected.getY();
			int width = objectSelected.getObjectWidth();
			int height = objectSelected.getObjectHeight();

			if (((x2 > (x1 - 5)) && (x2 < (x1 + 5))) && ((y2 > (y1 - 5)) && (y2 < (y1 + 5))))
			{
				Cursor = "Cursor.NW_RESIZE_CURSOR";
				if (w - x2 > 20 && h - y2 > 20)
				{
					w = w - x2;
					h = h - y2;
					objectSelected.setObjectSize(x + x2, y + y2, w, h);
				}
			}
			else if (((x2 > (x1 + width - range)) && (x2 < (x1 + width + range))) && ((y2 > (y1 - range)) && (y2 < (y1 + range))))
			{
				Cursor = "Cursor.NE_RESIZE_CURSOR";
			}
			else if (((x2 > (x1 - range)) && (x2 < (x1 + range))) && ((y2 > (y1 + height - range)) && (y2 < (y1 + height + range))))
			{
				Cursor = "Cursor.SW_RESIZE_CURSOR";
			}
			else if (((x2 > (x1 - range + width)) && (x2 < (x1 + range + width))) && ((y2 > (y1 + height - range)) && (y2 < (y1 + height + range))))
			{
				Cursor = "Cursor.SE_RESIZE_CURSOR";
			}
			else
			{
				Cursor = null;
				objectSelected.setBackGround("BLACK");

			}
			if (Cursor != null)
			{
				x = x1;
				y = y1;
				w = width;
				h = height;
				// gamePlayArea.setRectColor(Color.red);
				objectSelected.setResize(true);

			}
			else
			{
				objectSelected.setResize(false);

			}
		}
	}

	public void setSize(int x2, int y2)
	{

		int x = 0, y = 0, w = 0, h = 0;
		if (objectSelected != null)
		{
			x = objectSelected.getX();
			y = objectSelected.getY();
			w = objectSelected.getObjectWidth();
			h = objectSelected.getObjectHeight();

			if (Cursor == "Cursor.NW_RESIZE_CURSOR")
			{
				w = w + x - x2;
				h = h + y - y2;
				objectSelected.setObjectSize(x2, y2, w, h);
			}

			if (Cursor == "Cursor.NE_RESIZE_CURSOR")
			{
				w = x2 - x;
				h = h + y - y2;
				objectSelected.setObjectSize(x, y2, w, h);
			}

			if (Cursor == "Cursor.SE_RESIZE_CURSOR")
			{
				w = x2 - x;
				h = y2 - y;
				objectSelected.setObjectSize(x, y, w, h);
			}

			if (Cursor == "Cursor.SW_RESIZE_CURSOR")
			{
				w = w + x - x2;
				h = y2 - y;
				objectSelected.setObjectSize(x2, y, w, h);
			}
			x = objectSelected.getX();
			y = objectSelected.getY();
			w = objectSelected.getObjectWidth();
			h = objectSelected.getObjectHeight();
		}
	}

	public void changeSelectedObjectLocal(int x, int y)
	{
		if (Cursor == null)
		{

			if (objectSelected != null)
			{

				objectSelected.setX(objectSelected.getX() - (begin_x - x));
				objectSelected.setY(objectSelected.getY() - (begin_y - y));
				begin_x = x;
				begin_y = y;
			}

		}
	}

	/* Method mouseDragged : Mose dragged event */
	public void mouseDragged(MouseEvent mouseDrag)
	{
		// TODO Auto-generated method stub
		if (isObjectSelected == true)
		{
			if (Cursor == null)
				changeSelectedObjectLocal((int) (mouseDrag.getX() / gameBoard.getCurrentHeightRate()), (int) (mouseDrag.getY() / gameBoard.getCurrentWidthRate()));
			objectSelected.setResize(true);
		}
		this.setSize((int) (mouseDrag.getX() / gameBoard.getCurrentHeightRate()), (int) (mouseDrag.getY() / gameBoard.getCurrentWidthRate()));

		gameBoard.getGameBoard().repaint();
		// GameCreatePanelController.getInstance().getGameCreatePanel().selectedSprite(objectSelected);
		GameMaker.logger.logInfo(this.getClass().getName() + " - Sprite dragged on board.");

	}

	/* Method mouseMoved :Mouse moved */
	public void mouseMoved(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	/* Method mouseClicked :mouse clicked event */
	public void mouseClicked(MouseEvent mouseEvent)
	{

		if (mouseEvent.getButton() == 3)
		{
			gameBoard.getGameBoard().requestFocus();
			/*
			 * if(!timer.isRunning()) { resetTimer(); timer.start(); } else
			 * timer.stop();
			 */
			GameBoardRightClick rightClickMenu = new GameBoardRightClick();
			rightClickMenu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
			// TODO: Start stop controller will take care of timer start and
			// stop.
		}
		if (mouseEvent.getClickCount() == 2)
		{
			setSelectObjectLocation();
			this.setCursor((int) ((double) mouseEvent.getX() / gameBoard.getCurrentHeightRate()), (int) (mouseEvent.getY() / gameBoard.getCurrentWidthRate()));

		}

		// if (!GameMaker.timerEvent.isPlayMode())
		for (int spriteCounter = gameBoardModel.getSpriteList().size() - 1; spriteCounter >= 0 && isObjectSelected == false; spriteCounter--)
		{

			Sprite sprite = gameBoardModel.getSpriteList().get(spriteCounter);
			for (int objectCounter = 0; objectCounter < sprite.getGameObjects().size(); objectCounter++)
			{

				if (checkSelected(sprite.getGameObjects().get(objectCounter), (int) (mouseEvent.getX() / gameBoard.getCurrentHeightRate()), (int) (mouseEvent.getY() / gameBoard.getCurrentWidthRate())))
					break;
			}

			if (isObjectSelected)
				break;
		}
		gameBoard.getGameBoard().repaint();
	}

	/* Method checkSelected :Checks the selected event. */
	private boolean checkSelected(GameObject object, int x, int y)
	{
		if ((object.getX() <= x) && (x <= (object.getX() + object.getObjectWidth())) && (object.getY() <= y) && (object.getY() + object.getObjectHeight() >= y))
		{
			objectSelected = object;
			objectSelected.setSelected(true);
			isObjectSelected = true;
			objectSelecteds.add(objectSelected);
			// GameCreatePanelController.getInstance().getGameCreatePanel().selectedSprite(objectSelected);
			GameMaker.logger.logInfo(this.getClass().getName() + object.getObjectId() + " - Sprite selected on mouse click.");
		}
		else
		{
			isObjectSelected = false;
			for (GameObject objected : objectSelecteds)
			{
				objected.setSelected(false);
				objected.setResize(false);
			}
		}
		gameBoard.getGameBoard().repaint();
		return isObjectSelected;

	}

	/* Method mouseEntered : Mouse entered event */
	public void mouseEntered(MouseEvent arg0)
	{ // TODO Auto-generated method stub

	}

	/* Method mouseExited: Mouse exited event */
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
	}

	/* Method mousePressed : Mose pressed event */
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	/* Method mouseReleased : Mouse released event */
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		isObjectSelected = false;
	}

	public void setGameBoard(GameBoard gameboard)
	{
		gameBoard = gameboard;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		boolean secondAdded = updateTime();
		for (int i = 0; i < getGameBoardModel().getEventList().size(); i++)
		{
			Event eventInstance = getGameBoardModel().getEventList().get(i);
			if (eventInstance instanceof TimerEvent)
			{
				eventInstance.performActions();

			}
			else if (eventInstance instanceof CollisionEvent)
			{
				((CollisionEvent) eventInstance).checkCollision();

			}
			else if (eventInstance instanceof GenerateRandom && secondAdded)
			{
				if ((getSecondsElapsed() <= ((GenerateRandom) eventInstance).getMaxTime()) && (getSecondsElapsed() >= ((GenerateRandom) eventInstance).getStartTime()) && ((getSecondsElapsed() % ((GenerateRandom) eventInstance).getDelay()) == 0))
				{
					eventInstance.performActions();
				}
			}
			else if (eventInstance instanceof GenerateAtPosition && secondAdded)
			{
				if ((getSecondsElapsed() <= ((GenerateAtPosition) eventInstance).getMaxTime()) && (getSecondsElapsed() >= ((GenerateAtPosition) eventInstance).getStartTime()) && ((getSecondsElapsed() % ((GenerateAtPosition) eventInstance).getDelay()) == 0))
				{
					eventInstance.performActions();
				}
			}
			else if (eventInstance instanceof CollisionEventWithException)
			{
				((CollisionEventWithException) eventInstance).checkCollision();

			}
			else if (eventInstance instanceof CountEvent)
			{
				eventInstance.performActions();

			}

		}

		if (secondAdded)
			// clearUnusedObjects();

			/*
			 * int count = 0; Iterator<Sprite> itr =
			 * gameBoardModel.getSpriteList().iterator(); while(itr.hasNext()) {
			 * count = count + itr.next().getGameObjects().size();
			 * 
			 * } //System.out.println("Object count: " + count);
			 */
			if (isStopGameFlag())
			{
				timer.stop();
				
				if(!GameMakerParams.getInstance().isGameMakerMode())
				{
					int selection;
					selection = JOptionPane.showConfirmDialog(null, "Do you wish to save scores?");
					if(selection==0)
					{
					long userId = GameMakerParams.getInstance().getUserID();
					long gameId = GameMakerParams.getInstance().getGameID();
					int score = GameBoardController.getInstance().getScoreCount();
					boolean isWin = true;
					DatabaseConnector dbConn = DatabaseConnector.getInstance();
			         
			         dbConn.createConnection();
			         dbConn.saveScores(userId, gameId, score, isWin);
			         //dbConn.selectGames();
			         dbConn.shutdown();
			         JOptionPane.showMessageDialog(null, "Your scores is saved in our database successfully");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You decided not to save the scores");
					}
			         
				}
			}

		gameBoard.getGameBoard().repaint();

	}

	/* Method keyPressed : Takes care of the key pressed */
	public void keyPressed(KeyEvent keyEvent)
	{
		for (int i = 0; i < getGameBoardModel().getEventList().size(); i++)
		{
			Event eventInstance = getGameBoardModel().getEventList().get(i);
			if (eventInstance instanceof KeyPressedEvent)
			{
				if (((KeyPressedEvent) eventInstance).isEventTriggered(keyEvent))
				{
					eventInstance.performActions();
				}
			}
		}
	}

	/* Method keyReleased: Key released event */
	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	/* Method Key Typed : key typed event */
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	public void setGameMacroCommand(GameMacroCommand gameMacroCommand)
	{
		this.gameMacroCommand = gameMacroCommand;
	}

	public GameMacroCommand getGameMacroCommand()
	{
		return gameMacroCommand;
	}

	public void saveIntialPositions()
	{
		getGameMacroCommand().removeAll();
		// System.out.println("Sprite list Size: " +
		// gameBoardModel.getSpriteList().size());
		for (Sprite sprite : gameBoardModel.getSpriteList())
		{
			getGameMacroCommand().add(new SpriteCommand(sprite));
		}
		getGameMacroCommand().Do();
	}

	/*
	 * Load intial positions.
	 */
	public void loadIntialPositions()
	{
		getGameMacroCommand().Undo();
		setGameVariables();

		gameBoard.getGameBoard().repaint();
	}

	public void clearUnusedObjects()
	{
		Iterator<Sprite> itrSprite = gameBoardModel.getSpriteList().iterator();
		while (itrSprite.hasNext())
			itrSprite.next().removeUnunsedObjects();
	}

	public boolean betweenInclusive(int value, int min, int max)
	{
		return value >= min && value <= max;
	}

	public Timer getTimer()
	{
		return timer;
	}

	public void setTimer(Timer timer)
	{
		this.timer = timer;
	}

	public void setGameTime(JTextField gameTime)
	{
		this.gameTime = gameTime;
	}

	public JTextField getGameTime()
	{
		return gameTime;
	}

	public JLabel getGameScore()
	{
		return gameScore;
	}

	public void setGameScore(JLabel gameScore)
	{
		this.gameScore = gameScore;
	}

	public int getScoreCount()
	{
		return scoreCount;
	}

	public int incrementScoreCountBy(int incrementValue)
	{
		return (this.scoreCount += incrementValue);
	}

	public void setScoreCount(int scoreCount)
	{
		this.scoreCount = scoreCount;
	}

	public void resetScore()
	{
		this.scoreCount = 0;
	}
}