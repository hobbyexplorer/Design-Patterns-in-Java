package model.action;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.SaveGame;
import gamemaker.Constants;
import model.GameObject;
import model.Sprite;

public class MoveAlong implements Action
{
	private int actionType = 2;
	private Sprite sprite;
	private String actionName;

	public MoveAlong(Sprite sprite)
	{
		this.actionName = Constants.ACTION_MOVE_ALONG;
		this.sprite = sprite;
	}
	
	@Override
	public void execute()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(GameObject gameObject)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(GameObject moving, GameObject toBeMoved)
	{
		toBeMoved.moveObjectBy(moving.getAngle(), moving.getSpeed());
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
				
		return actionElement;
	}


}
