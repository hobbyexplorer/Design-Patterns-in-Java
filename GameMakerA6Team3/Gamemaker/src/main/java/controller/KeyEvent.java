package controller;

import gamemaker.Constants;
import gamemaker.GameBoard;
import gamemaker.GameMaker;

/**
 * Class name: KeyEvent Class responsibilities: Responsible for the various key
 * events Class Collaborators:
 */
public class KeyEvent
{
	// The check collision.
	private CheckCollision checkCollision;

	// The game board.
	private GameBoard gameBoard;

	// The timer event.
	private TimerEvent timerEvent;

	// Constructor KeyEvent: Instantiates a new key event.
	public KeyEvent(GameBoard gameBoard, TimerEvent timerEvent)
	{
		this.gameBoard = gameBoard;
		this.timerEvent = timerEvent;
	}

	/*
	 * Method performKeyAction: Perform Necessary Action associated with Key
	 * Event.
	 */
	/*public void performKeyAction(String keyCharacter)
	{
		checkCollision = new CheckCollision(gameBoard.getGameBoard().getWidth(), gameBoard.getGameBoard().getHeight());
		// if game is in playing mode
		if (this.timerEvent.isPlayMode())
		{
			String event = "";
			if (keyCharacter.equals("Up"))
				event = Constants.EVENT_KEY_UP;
			else if (keyCharacter.equals("Down"))
				event = Constants.EVENT_KEY_DOWN;
			else if (keyCharacter.equals("Left"))
				event = Constants.EVENT_KEY_LEFT;
			else if (keyCharacter.equals("Right"))
				event = Constants.EVENT_KEY_RIGHT;
			// for each sprite call relevant action to be executed
			for (int i = 0; i < GameMaker.spriteList.getObjectList().size(); i++)
			{
				SpriteObject sprite = GameMaker.spriteList.getObjectList().get(i);
				if (sprite.getEventActionVector().contains(event + " - " + "Move Action"))
				{
					MoveAction moveSprite = new MoveAction(event);
					moveSprite.actionExecute(sprite);
					checkCollision.checkWallCollisionOnKey(sprite);
					checkCollision.checkSpriteCollision(sprite);
					gameBoard.getGameBoard().repaint();
				}
				if (sprite.getEventActionVector().contains(event + " - " + "Sound Action"))
				{
					SoundAction sound = new SoundAction(sprite.getSoundFileToEventMap().get(event));
					sound.actionExecute(sprite);
				}
				if (sprite.getEventActionVector().contains(event + " - " + "Destroy Action"))
				{
					DestroyAction destroy = new DestroyAction();
					destroy.actionExecute(sprite);
					gameBoard.getGameBoard().repaint();
				}
				if (sprite.getEventActionVector().contains(event + " - " + "Rotate Anticlockwise Action"))
				{
					RotateAnticlockwiseAction rotateAction = new RotateAnticlockwiseAction(event);
					rotateAction.actionExecute(sprite);
					gameBoard.getGameBoard().repaint();
				}
			}
			// check for victory condition
			if (GameMaker.victoryEvent.checkVictoryLose().equals("Win"))
			{
				GameMaker.timerEvent.setPlayMode(false);
				GameBoardController.getInstance().getGameBoard().setWinvalue(true);
				GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();
			}
			// check for losing condition
			else if (GameMaker.victoryEvent.checkVictoryLose().equals("Lose"))
			{
				GameMaker.timerEvent.setPlayMode(false);
				GameBoardController.getInstance().getGameBoard().setLosevalue(true);
				GameBoardController.getInstance().getGameBoard().getGameBoard().repaint();
			}

		}

	}
*/}
