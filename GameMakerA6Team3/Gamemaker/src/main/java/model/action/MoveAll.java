package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class MoveAll implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private String actionName;
	private int angle;
	private int speed;

	public MoveAll(Sprite sprite, int angle, int speed)
	{
		this.actionName = Constants.ACTION_MOVE_ALL;
		this.sprite = sprite;
		this.angle = angle;
		this.speed = speed;
	}

	@Override
	public void execute()
	{

	}

	@Override
	public void execute(GameObject gameObject)
	{
		GameObject newObject;
		for(int index=0; index<this.sprite.getGameObjects().size(); index++)
		{
			newObject = this.sprite.getGameObjects().get(index);
			newObject.moveObjectBy(this.angle, this.speed);
		}
	}

	@Override
	public void execute(GameObject gameObject1, GameObject gameObject2)
	{

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
		
		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "actionString", actionName);
		saveGame.createNode(actionElement, "angle", Integer.toString(angle));
		saveGame.createNode(actionElement, "speed", Integer.toString(speed));
		
		return actionElement;
	}

}
