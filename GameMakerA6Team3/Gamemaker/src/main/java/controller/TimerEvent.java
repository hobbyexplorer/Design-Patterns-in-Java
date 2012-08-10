package controller;

import gamemaker.GameBoard;
import gamemaker.GameMaker;

import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * Class Name: TimerEvent. Class responsibility: Contains the timer event Class
 * Collaborators:
 */
public class TimerEvent implements Runnable
{

	// The is play mode.
	private boolean isPlayMode = false;

	// The check collision.
	private CheckCollision checkCollision;

	// The game board.
	private GameBoard gameBoard;

	// The formatter.
	NumberFormat formatter = new DecimalFormat("00");

	// Constructor TimerEvent: Instantiates a new timer event.
	public TimerEvent(GameBoard gameBoard)
	{

		this.gameBoard = gameBoard;
	}

	/* Method run: */
	@Override
	public void run()
	{
/*
		//checkCollision = new CheckCollision(gameBoard.getGameBoard().getWidth(), gameBoard.getGameBoard().getHeight());
		while (true)
		{
			try
			{
				Thread.sleep(0);
				if (isPlayMode)
				{
					for (int i = 0; i < GameMaker.spriteList.getObjectList().size(); i++)
					{
						SpriteObject sprite = GameMaker.spriteList.getObjectList().get(i);
						if (sprite.getEventActionVector().contains("Timer Event" + " - " + "Move Action"))
						{
							MoveAction moveSprite = new MoveAction("Timer Event");
							moveSprite.actionExecute(sprite);
							checkCollision.checkWallCollision(sprite);
							checkCollision.checkSpriteCollision(sprite);
							gameBoard.getGameBoard().repaint();
						}
						if (sprite.getEventActionVector().contains("Timer Event" + " - " + "Sound Action"))
						{
							SoundAction sound = new SoundAction(sprite.getSoundFileToEventMap().get("Timer Event"));
							sound.actionExecute(sprite);
						}
					}
					if (GameMaker.victoryEvent.checkVictoryLose().equals("Win"))
					{
						GameMaker.timerEvent.setPlayMode(false);
						GameBoardController.getInstance().getGameBoard().setWinvalue(true);
						GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();
					}
					else if (GameMaker.victoryEvent.checkVictoryLose().equals("Lose"))
					{
						GameMaker.timerEvent.setPlayMode(false);
						GameBoardController.getInstance().getGameBoard().setLosevalue(true);
						GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();
					}
					Thread.sleep(20);
					GameMaker.timercount += 20;
					setTime();
				}
			}
			catch (Exception e)
			{
				// add exception handling
			}
		}

	}

	 Method isPlayMode : Checks if is play mode. 
	public boolean isPlayMode()
	{
		return isPlayMode;
	}

	 Method setPlayMode: Sets the play mode. 
	public void setPlayMode(boolean isPlayMode)
	{
		this.isPlayMode = isPlayMode;
	}

	
	 * Method setTime: displays the game time in minutes and seconds
	 
	public void setTime()
	{
		// timer for seconds
		if (GameMaker.timercount < 60000)
		{
			String second = formatter.format(GameMaker.timercount / 1000);
			GameMaker.gameTime.setText("00:" + "00:" + second);

		}
		// timer for minutes
		else if (GameMaker.timercount > 60000)
		{
			String minute = formatter.format(GameMaker.timercount / 60000);
			String second = formatter.format((GameMaker.timercount % 60000) / 1000);
			GameMaker.gameTime.setText("00:" + minute + ":" + second);
		}

*/	}
}
