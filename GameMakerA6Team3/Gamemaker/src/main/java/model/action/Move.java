package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class Move implements Action
{
	private int actionType = 1;
	private Sprite sprite;
	private String actionName;
	private int angle;

	public Move(Sprite sprite, int angle)
	{
		this.actionName = Constants.ACTION_MOVE;
		this.sprite = sprite;
		this.angle = angle;
		sprite.setAngle(angle);
	}

	@Override
	public void execute()
	{

	}

	@Override
	public void execute(GameObject gameObject)
	{
		gameObject.moveObject();

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
				
		return actionElement;
	}

}
