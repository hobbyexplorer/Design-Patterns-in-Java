package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class Rotate implements Action
{

	private int actionType = 1;
	private Sprite sprite;
	private int angle;
	private String actionName;

	public Rotate(Sprite sprite, int angle)
	{
		this.actionName = Constants.ACTION_ROTATE;
		this.sprite = sprite;
		this.angle = angle;
		// sprite.setSpriteAndObjectDelta(angle);
	}

	@Override
	public void execute()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void execute(GameObject gameObject)
	{
		// gameObject.initializeRotation();
		gameObject.updateAngle(this.angle);
		gameObject.updateImageRotationAngle(this.angle);


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
		
		saveGame.createNode(actionElement, "actionsprite", Integer.toString(sprite.getSpriteId()));
		saveGame.createNode(actionElement, "actionString", actionName);
		saveGame.createNode(actionElement, "angle", Integer.toString(angle));
				
		return actionElement;
	}

}
