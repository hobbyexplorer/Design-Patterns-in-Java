package model.action;

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

public class Shoot implements Action
{

	private GameBoardModel gameBoardModel;
	private int actionType = 1;
	private Sprite sprite;
	private Sprite shootSprite;
	private TimerEvent timerEvent;
	private String actionName;

	public Shoot(Sprite sprite, Sprite shootSprite)
	{
		this.actionName = Constants.ACTION_SHOOT;
		// gameBoardModel =
		// GameBoardController.getInstance().getGameBoardModel();
		this.sprite = sprite;
		this.shootSprite = shootSprite;

		// timerEvent = new TimerEvent(shootSprite);
		// Move action = new Move(shootSprite, 45);
		// timerEvent.addAction(action);
		// gameBoardModel.addEvent(timerEvent);
		// shootSprite.addGameObject();
	}

	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameObject gameObject)
	{
		GameObject newObject;
		newObject = shootSprite.addGameObject();
		newObject.setX(gameObject.getX() + (gameObject.getObjectWidth() / 2));
		newObject.setY(gameObject.getY() + (gameObject.getObjectHeight() / 2));
		newObject.setAngle(gameObject.getAngle());
	//	newObject.setImageRotationAngle(gameObject.getImageRotationAngle());
	//	newObject.setSpeed(10);
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
	
	@Override
	public Element getActionInfoXML(SaveGame saveGame) {
		Document document = saveGame.getDocument();
		Element actionElement = document.createElement("action");
		
		saveGame.createNode(actionElement, "actionString", actionName);
		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "shootSprite", Integer.toString(shootSprite.getSpriteId()));
				
		return actionElement;
	}

}
