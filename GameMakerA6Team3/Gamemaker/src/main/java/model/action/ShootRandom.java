package model.action;

import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.GameBoardController;
import controller.SaveGame;
import gamemaker.Constants;
import gamemaker.GameBoard;
import model.GameBoardModel;
import model.GameObject;
import model.Sprite;
import model.event.TimerEvent;

public class ShootRandom implements Action
{

	private GameBoardModel gameBoardModel;
	private int actionType = 0;
	private Sprite sprite;
	private Sprite shootSprite;
	private TimerEvent timerEvent;
	private String actionName;
	private int angle;
	private int speed;
	private long shootDelay;
	private long lastShootTime = 0;
	private Random generator;

	public ShootRandom(Sprite sprite, Sprite shootSprite, long shootDelay, int angle, int speed)
	{
		this.actionName = Constants.ACTION_SHOOT_RANDOM;
		// gameBoardModel =
		// GameBoardController.getInstance().getGameBoardModel();
		this.sprite = sprite;
		this.shootSprite = shootSprite;
		this.shootDelay = shootDelay;
		this.angle = angle;
		this.speed = speed;
		generator = new Random();

		// timerEvent = new TimerEvent(shootSprite);
		// Move action = new Move(shootSprite, 45);
		// timerEvent.addAction(action);
		// gameBoardModel.addEvent(timerEvent);
		// shootSprite.addGameObject();
	}

	@Override
	public void execute()
	{
		long currTime = System.currentTimeMillis();
		long elapsedTime = currTime - lastShootTime;
		if (elapsedTime >= this.shootDelay)
		{

			GameObject newObject;
			newObject = shootSprite.addGameObject();

			int selectedObjectIndex = randomGenerator(this.sprite.getGameObjects().size());
			GameObject gameObject = this.sprite.getGameObjects().get(selectedObjectIndex);
			newObject.setX(gameObject.getX() + (gameObject.getObjectWidth() / 2));
			newObject.setY(gameObject.getY() + (gameObject.getObjectHeight() / 2));
			newObject.setAngle(this.angle);
			newObject.setSpeed(this.speed);
		
			this.lastShootTime = currTime;
		}

		}

		@Override
		public void execute(GameObject gameObject)
		{
		}

		@Override
		public void execute(GameObject gameObject1, GameObject gameObject2)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public int getSpriteId()
		{
			return this.sprite.getSpriteId();
		}

		@Override
		public int getActionType()
		{
			return this.actionType;
		}

		@Override
		public String getActionName()
		{
			return this.actionName;
		}

		public int randomGenerator(int range)
		{
			return generator.nextInt(range);
		}
		
		@Override
		public Element getActionInfoXML(SaveGame saveGame) {
			Document document = saveGame.getDocument();
			Element actionElement = document.createElement("action");
			
			saveGame.createNode(actionElement, "actionString", actionName);
			saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
			saveGame.createNode(actionElement, "shootSprite", Integer.toString(shootSprite.getSpriteId()));
			saveGame.createNode(actionElement, "shootDelay", Long.toString(shootDelay));
			saveGame.createNode(actionElement, "angle", Integer.toString(angle));
			saveGame.createNode(actionElement, "speed", Integer.toString(speed));
			
			return actionElement;
		}

	}
